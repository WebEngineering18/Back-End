## FragebogenIFB

### Installationsanleitung

#### 1. Installation der Datenbank

1. postgres Datenbankserver aufsetzen (9.3 - 10.5 kompatibel)
2. Zum Datenbankserver verbinden (PgAdmin oder DBeaver öffnen)
3. Rechtsklick auf Servers in DBeaver oder PgAdmin -> Verbinden
4. Auf Datenbanken klicken und mit Rechtsklick -> neue Datenbank
5. Zieldatenbank anlegen (empfohlener Datenbankname: fragebogen)

#### 2. Installation Payara-Server
1. Payara herunterladen (https://www.payara.fish/all_downloads)
1.1 Payara in ein Verzeichnis entpacken (Der Pfad darf keine Leerzeichen enthalten)
2. Postgres Datenbanktreiber installieren
2.1 Aktuellen JDBC-Treiber für Postgres herunterladen (https://jdbc.postgresql.org/download.html)
2.2 Treiber jar-File in das Verzeichnis /PAYARA_ROOT/glassfish/domains/domain1/lib kopieren
2.3 Payara-Server neu starten

#### 3. JDBC-Connection Pool anlegen
1.   Payara starten
1.1. Payara-Admin Oberfläche aufrufen (localhost:4848 in Browser eingeben)
2.   Menü: Resources / JDBC / JDBC Connection Pools
2.1. Button "New"
2.2. Poolname: ifbf_umfragePool
2.3. Resource Type: javax.sql.DataSource
2.4. Database Driver Vendor: Postgresql
2.5. Button "Next"
3.1. Unter "Additional Properties"
3.2. Add Property 
3.3. Name: URL
3.4. Value: jdbc:postgresql://localhost:5432/fragebogen
3.5. User für Lokale Benutzung (postgres) 
   und Passwort ("YourPassword") eingeben
3.6. Save Button klicken 
3.7. Im Menü zu JDBC/JDBC Connection Pools/ifbf_umfragePool navigieren -> Ping klicken

#### 4. Datenbanksource anlegen
1. Im Menü zu JDBC/JDBC Resources klicken
2. New... klicken
3. JNDI Name: jdbc/ifbf_umfrage eingeben
4. Pool Name: ifbf_umfragePool
5. OK Button klicken
6. Save Button klicken

#### 5. SmartMonitoringBackend installieren
1. Aktuelle Version aus dem Repository laden
2. Payara starten
3. Payara-Admin Oberfläche aufrufen (localhost:4848 in Browser eingeben)
4. Menü: "Applications"
5. Button: "Deploy"
6. war-Archiv (umfrageIFB-1.0.war) auswählen
7. ContextRoot : "/umfrageIFB" schreiben
8. ApplicationName : "umfrageIFB" schreiben
9. Button "ok"

#### 6. Seite Starten

1 Backend starten
1.1 Öffnen Sie einen Browser
1.2 Geben Sie die Adresse http://localhost:8080/umfrageIFB/saveAllQuestions ein um die Fragen in der Datenbank zu speichern.
1.3 Geben Sie die Adresse http://localhost:8080/umfrageIFB/index.hmtl ein und folgen Sie den Anweisungen auf der Startseite.

