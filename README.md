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
      * Payara in ein Verzeichnis entpacken (Der Pfad darf keine Leerzeichen enthalten)
2. Postgres Datenbanktreiber installieren
      * Aktuellen JDBC-Treiber für Postgres herunterladen (https://jdbc.postgresql.org/download.html)
      * Treiber jar-File in das Verzeichnis /PAYARA_ROOT/glassfish/domains/domain1/lib kopieren
      * Payara-Server neu starten

#### 3. JDBC-Connection Pool anlegen
1.   Payara starten
      * Payara-Admin Oberfläche aufrufen (localhost:4848 in Browser eingeben)
2.   Menü: Resources / JDBC / JDBC Connection Pools
      * Button "New"
      * Poolname: ifbf_umfragePool
      * Resource Type: javax.sql.DataSource
      * Database Driver Vendor: Postgresql
      * Button "Next"
3.   Unter "Additional Properties"
      * Add Property 
      * Name: URL
      * Value: jdbc:postgresql://localhost:5432/fragebogen
      * User für Lokale Benutzung (postgres) und Passwort ("YourPassword") eingeben
      * Save Button klicken 
      * Im Menü zu JDBC/JDBC Connection Pools/ifbf_umfragePool navigieren -> Ping klicken

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

1. Backend starten
      * Öffnen Sie einen Browser
      * Geben Sie die Adresse http://localhost:8080/umfrageIFB/saveAllQuestions ein um die Fragen in der Datenbank zu speichern.
      * Geben Sie die Adresse http://localhost:8080/umfrageIFB/index.hmtl ein und folgen Sie den Anweisungen auf der Startseite.

