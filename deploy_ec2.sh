#!/bin/bash
# Script completo de despliegue en EC2 para el sistema de reservas de viajes

# 1. Actualizar el sistema
echo "Actualizando el sistema..."
sudo yum update -y

# 2. Instalar Java 17
echo "Instalando Java 17..."
sudo amazon-linux-extras enable java-openjdk17 -y
sudo yum install java-17-openjdk -y

# 3. Verificar instalación de Java
echo "Verificando versión de Java..."
java -version

# 4. Subir archivo JAR (desde máquina local con scp)
echo "Copie su archivo JAR usando:"
echo "scp -i <mi_clave.pem> target/sistema-viajes-0.0.1-SNAPSHOT.jar ec2-user@<IP-EC2>:~"

# 5. Ejecutar el JAR
echo "Ejecutando la aplicación..."
nohup java -jar sistema-viajes-0.0.1-SNAPSHOT.jar > log_app.out 2>&1 &

# 6. Confirmar despliegue
echo "Aplicación ejecutándose en el puerto 8080."
echo "Acceda a: http://<IP-EC2>:8080"