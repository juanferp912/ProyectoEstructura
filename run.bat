@echo off
REM Script para compilar y ejecutar el Main del proyecto Estructura de Datos

echo ========== COMPILANDO PROYECTO ==========
javac *.java

if %errorlevel% equ 0 (
    echo [OK] Compilacion exitosa
    echo.
    echo ========== EJECUTANDO MAIN ==========
    java Main
) else (
    echo [ERROR] Error en la compilacion
    exit /b 1
)
