# battleship_war

## Debian(OS Linux)
- start mongodb
    - sudo systemctl start mongod
    - en caso de faild
        - sudo systemctl daemon-reload
        - y luego el comando start
- Verifique que MongoDB se haya iniciado correctamente
    - sudo systemctl status mongod
- MongoDB comience después de reiniciar el sistema
    - sudo systemctl enable mongod
- stop mongodb
    - sudo systemctl stop mongod
    - sudo systemctl is-active mongod
- reboot mongodb
    - sudo systemctl restart mongod
- comienza a usar mongodb 
    - mongosh
    - mongod
    - el servidor que se esté ejecutando en su host local con el puerto predeterminado 27017.
- version
    - mongod -version
- [documentacion de instalacion debian](https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-debian/)