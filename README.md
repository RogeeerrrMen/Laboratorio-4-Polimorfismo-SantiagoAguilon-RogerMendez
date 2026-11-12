# Laboratorio 4 - Polimorfismo
## ArenaGT Lite

**Curso:** CC2008 - Programación Orientada a Objetos  
**Universidad:** Universidad del Valle de Guatemala  
**Integrantes:** Santiago Aguilón #26199 y Roger Méndez #26786

## Descripción

ArenaGT es un programa que permite administrar una copa de deportes entre empresas. El sistema registra equipos, encuentros de fútbol sala y baloncesto, además de exhibiciones deportivas.


## Funcionalidades

- Registrar equipos con su código, nombre y empresa.
- Crear encuentros de fútbol sala y baloncesto.
- Crear exhibiciones deportivas.
- Programar y reprogramar eventos.
- Registrar resultados de encuentros.
- Calcular los puntos de tabla según las reglas de cada deporte.
- Mostrar la tabla de posiciones ordenada por puntos.
- Mostrar el calendario de eventos en orden cronológico.
- Validar resultados y conflictos de programación.


## Compilación y ejecución

Abrir una terminal desde la carpeta principal del proyecto.

**1. Compilar los archivos**

```bash
javac -d bin src/*.java
```

**2. Ejecutar el programa**

```bash
java -cp bin Main
```

Cuando empieza el programa carga cuatro equipos, cuatro encuentros deportivos y una exhibición que fueron establecidas para las pruebas.

## Menú principal

El programa presenta las siguientes opciones:

```text
===== ARENAGT LITE =====
1. Registrar equipo
2. Crear encuentro
3. Crear exhibicion
4. Programar o reprogramar evento
5. Registrar resultado
6. Mostrar tabla de posiciones
7. Mostrar calendario
8. Salir
```

El menú permanece disponible hasta que el usuario selecciona la opción de salir.


## Documentación

El análisis de clases, el diagrama UML se encuentran en la parte de la documentación del laboratorio.