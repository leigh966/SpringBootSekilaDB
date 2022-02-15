sudo apt update -y && sudo apt upgrade -y

chmod +x JavaMaven.sh
source JavaMaven.sh

mvn clean compile assembly:single
