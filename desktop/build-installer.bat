@echo off
echo ============================================
echo   Optica Demo - Build & Create Installer
echo ============================================
echo.

echo [1/4] Compilando y empaquetando...
call mvn clean package -q
if errorlevel 1 (
    echo ERROR: La compilacion fallo.
    pause
    exit /b 1
)
echo Build exitoso.

echo Copiando JAR principal a libs...
copy /Y target\optica-demo-desktop-1.0.0.jar target\libs\ >nul
echo.

echo [2/4] Buscando jpackage...
for /f "delims=" %%i in ('where jpackage 2^>nul') do set JPACKAGE=%%i
if not defined JPACKAGE (
    for /f "delims=" %%i in ('where javac 2^>nul') do set JAVAC=%%i
    for %%A in ("%JAVAC%") do set JDK_DIR=%%~dpA..
    set JPACKAGE=%JDK_DIR%\bin\jpackage.exe
    if not exist "%JPACKAGE%" (
        echo ERROR: jpackage no encontrado. Se requiere JDK 17+.
        pause
        exit /b 1
    )
)
echo jpackage: %JPACKAGE%
echo.

echo [3/4] Creando instalador MSI...
mkdir installer 2>nul

"%JPACKAGE%" ^
    --input target\libs ^
    --main-jar optica-demo-desktop-1.0.0.jar ^
    --main-class com.marcosmoreira.opticademo.app.AppLauncher ^
    --name "OpticaDemo" ^
    --type msi ^
    --icon logo.ico ^
    --win-dir-chooser ^
    --win-menu ^
    --win-shortcut ^
    --dest installer ^
    --app-version 1.0.0 ^
    --vendor "Marcos Moreira" ^
    --description "Sistema de gestion para opticas"

if errorlevel 1 (
    echo ERROR: jpackage fallo.
    pause
    exit /b 1
)

echo.
echo [4/4] Verificando instalador...
dir installer\*.msi 2>nul
if errorlevel 1 (
    echo ADVERTENCIA: El archivo MSI no se encontro en installer\
    pause
    exit /b 1
)

echo.
echo ============================================
echo   INSTALADOR CREADO EXITOSAMENTE!
echo ============================================
echo.
echo Archivo: installer\OpticaDemo-1.0.0.msi
echo.
echo IMPORTANTE - Advertencia de Windows SmartScreen:
echo Al ser una app sin certificado de firma digital,
echo Windows puede mostrar "Windows protegio tu PC".
echo Para instalar:
echo   1. Click en "Mas informacion"
echo   2. Click en "Ejecutar de todos modos"
echo   3. Seguir el asistente de instalacion
echo.
echo Para eliminar esta advertencia permanentemente
echo se requiere un "EV Code Signing Certificate"
echo (cuesta ~$100-400/ano).
echo.
pause
