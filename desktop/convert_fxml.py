#!/usr/bin/env python3
"""
Convert FXML files from horizontal SplitPane (table left, summary right) to vertical VBox (table top, summary below).
"""
import re
import os

BASE = r"C:\Users\MARCOS MOREIRA\Downloads\optica\blueprint\desktop\src\main\resources\fxml\modules"

FILES = [
    "seguimiento/SeguimientoView.fxml",
    "reportes/ReportesView.fxml",
    "seguros/SegurosView.fxml",
    "proveedores/ProveedoresView.fxml",
    "compras/ComprasView.fxml",
    "usuariosroles/UsuariosRolesView.fxml",  # note: actual file is UsuariosrolesView.fxml 
    "notificaciones/NotificacionesView.fxml",
    "ordeneslaboratorio/OrdenesLaboratorioView.fxml",  # note: actual file is OrdeneslaboratorioView.fxml
    "agenda/AgendaView.fxml",
    "sucursales/SucursalesView.fxml",
    "taller/TallerView.fxml",
    "recetas/RecetasView.fxml",
]

# Fix actual filenames
FILE_MAP = {
    "usuariosroles/UsuariosRolesView.fxml": "usuariosroles/UsuariosrolesView.fxml",
    "ordeneslaboratorio/OrdenesLaboratorioView.fxml": "ordeneslaboratorio/OrdeneslaboratorioView.fxml",
}

def convert_file(filepath):
    full_path = os.path.join(BASE, filepath)
    if not os.path.exists(full_path):
        # Try alternate
        for k, v in FILE_MAP.items():
            if filepath in k:
                full_path = os.path.join(BASE, v)
                break
    if not os.path.exists(full_path):
        print(f"NOT FOUND: {filepath}")
        return

    with open(full_path, 'r', encoding='utf-8') as f:
        content = f.read()

    original = content

    # 1. Replace SplitPane import with Separator import (if Separator not already present)
    if '<?import javafx.scene.control.Separator?>' not in content:
        content = content.replace(
            '<?import javafx.scene.control.SplitPane?>',
            '<?import javafx.scene.control.Separator?>'
        )
    else:
        content = content.replace('<?import javafx.scene.control.SplitPane?>', '')

    # Clean up blank import lines
    content = re.sub(r'\n\s*\n(?=\s*<\?import)', '\n', content)

    # 2. Replace the main SplitPane opening tag in <center>
    # Pattern: <center>\n        <SplitPane dividerPositions="..." styleClass="content-host">\n\n            <!-- LEFT: ... -->\n            <StackPane...>
    # Replace with: <center>\n        <VBox spacing="12" style="-fx-padding: 8;">\n            <!-- Table area -->\n            <StackPane VBox.vgrow="ALWAYS">
    
    # Match the <center> ... <SplitPane ...> ... <!-- LEFT: --> ... <StackPane pattern
    center_split = re.search(
        r'(<center>\s*\n\s*)<SplitPane[^>]*>(\s*\n\s*<!-- LEFT:[^-]*-->\s*\n\s*)(<StackPane[^>]*>)',
        content, re.IGNORECASE
    )
    if center_split:
        prefix = center_split.group(1)
        middle = center_split.group(2)
        stackpane = center_split.group(3)
        replacement = f'{prefix}<VBox spacing="12" style="-fx-padding: 8;">\n            <!-- Table area -->\n            {stackpane}'
        content = content[:center_split.start()] + replacement + content[center_split.end():]
    else:
        # Try alternative: <center> then SplitPane directly
        center_split2 = re.search(
            r'(<center>\s*\n\s*)<SplitPane[^>]*>(\s*\n\s*)(<VBox spacing="8")',
            content, re.IGNORECASE
        )
        if center_split2:
            prefix = center_split2.group(1)
            middle = center_split2.group(2)
            vbox = center_split2.group(3)
            replacement = f'{prefix}<VBox spacing="12" style="-fx-padding: 8;">\n            <!-- Table area -->\n            {vbox}'
            content = content[:center_split2.start()] + replacement + content[center_split2.end():]

    # 3. Replace closing: </StackPane>\n\n            <!-- RIGHT: ... -->\n            <VBox spacing="12" style="-fx-padding: 0 0 0 8;">
    # With: </StackPane>\n\n            <!-- Pagination bar -->\n            <fx:include fx:id="paginationBar" source="/fxml/shared/components/PaginationBar.fxml"/>\n\n            <!-- Summary section -->\n            <Separator/>\n            <Label text="..." style="..."/>\n\n            <!-- Summary panel -->\n            <VBox spacing="12" style="-fx-padding: 0;">
    
    # Find the transition from StackPane (left) to RIGHT VBox
    right_pattern = re.search(
        r'(</StackPane>\s*\n\s*)<!-- RIGHT:[^-]*-->\s*\n\s*(<VBox spacing="[^"]*" style="-fx-padding: 0 0 0 8;">)',
        content
    )
    if right_pattern:
        before = right_pattern.group(1)
        right_vbox = right_pattern.group(2)
        # Determine summary title from file
        fname = os.path.basename(filepath)
        module_name = fname.replace('View.fxml', '')
        replacement = f'{before}\n            <!-- Summary section -->\n            <Separator/>\n            <Label text="Resumen" style="-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: -optica-text-secondary;"/>\n\n            <!-- Summary panel -->\n            {right_vbox.replace("0 0 0 8", "0")}'
        content = content[:right_pattern.start()] + replacement + content[right_pattern.end():]

    # 4. Replace final </SplitPane> with </VBox>
    # Find the LAST </SplitPane> before </BorderPane>
    last_split = content.rfind('</SplitPane>')
    if last_split >= 0:
        border_pane = content.find('</BorderPane>')
        if border_pane > last_split:
            content = content[:last_split] + '</VBox>' + content[last_split + len('</SplitPane>'):]

    # 5. Remove any inner SplitPane that are VERTICAL (keep them - they're inside sub-views)
    # Actually, the instruction says to convert the MAIN SplitPane. Inner vertical SplitPanes within sub-views should stay.
    # But we need to remove the SplitPane import if it's no longer used.
    # Check if any SplitPane tags remain (excluding imports)
    remaining = re.findall(r'<SplitPane[^>]*/?>|</SplitPane>', content)
    if not remaining:
        content = content.replace('<?import javafx.scene.control.SplitPane?>\n', '')
        content = content.replace('<?import javafx.scene.control.SplitPane?>', '')

    # 6. Truncate at </BorderPane> if there's content after
    bp_idx = content.find('</BorderPane>')
    if bp_idx >= 0:
        end_idx = bp_idx + len('</BorderPane>')
        if len(content) > end_idx:
            content = content[:end_idx] + '\n'

    if content != original:
        with open(full_path, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f"CONVERTED: {filepath}")
    else:
        print(f"NO CHANGE: {filepath}")

def main():
    for f in FILES:
        convert_file(f)
    
    # Also handle the files with different casing
    convert_file("usuariosroles/UsuariosrolesView.fxml")
    convert_file("ordeneslaboratorio/OrdeneslaboratorioView.fxml")

if __name__ == '__main__':
    main()
