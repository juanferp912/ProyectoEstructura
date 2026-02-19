#!/bin/bash

# Script para compilar y ejecutar el Main del proyecto Estructura de Datos

echo "========== COMPILANDO PROYECTO =========="
javac *.java

if [ $? -eq 0 ]; then
    echo "✓ Compilación exitosa"
    echo ""
    echo "========== EJECUTANDO MAIN =========="
    java Main
else
    echo "✗ Error en la compilación"
    exit 1
fi
