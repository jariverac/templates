# Scaffold

## Propósito:

This is a Scaffold

## Instalación:

### Prerrequisitos:

- Java 21
- Maven 3.12.1
- Quarkus 3.8.5.SP1-redhat-00001
- Docker 24.0.6 (opcional para imágenes nativas)

### Construcción:

```
./mvnw clean install
```

### Ejecución (Local):

```
quarkus dev
```

### Ejecución (JVM):

```
./mvnw clean install -Dquarkus.kubernetes.deploy=true
```

### Ejecución (Nativo):

```
./mvnw clean install -Dnative -Dquarkus.kubernetes.deploy=true
```

## Uso:

### Headers:
```
```
### POST:
- http://localhost:8080/
```
{
  "mensaje": "Prueba",
}
```
