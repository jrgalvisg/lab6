FROM javergarav/tomee

COPY env/tomee.xml conf/tomee.xml
COPY env/server.xml conf/server.xml

COPY target/sa-auth-ms.war webapps/sa-auth-ms.war

EXPOSE 4000