# Podział na pakiety #


Model biznesowy - P. Ziemiańczyk
Priorytety:
  * Komunikacja Syn/Rodzic -> Serwer JBoss (Arek + Paweł)
  * Odczyt danych z GPS (Banasik)
  * Obsługa mapy (rysowanie punktów GPS itp) - Arek
  * Hibernate -> JBoss (Mróz, Bieniek)
  * Dane GPS -> Adres (Arek), jeszcze w tym tygodniu
  * Odczyt danych o lokalizacji na podstawie GSM->Adam


22.03. ok. 19:00 next session (dżem)
28.03. - spotkanie ze Skonecznym
Moduły systemu

> (1) aplikacja rodzica, (2) aplikacja dziecka, (3) aplikacja serwerowa, (4) aplikacja administratora

(A)dam, (Ar)ek, (G)rzesiek, (M)ateusz, (Mi)chał, (P)aweł
### Moduł rodzica: ###

pw.childcontrol.parent
  * (M)(G)----(1) pw.childcontrol.parent.servercommunication	// obsługa połączenia z serwerem


### Moduł dziecka: ###
pw.childcontrol.child
  * (Ar)(P)(2) pw.childcontrol.child.servercommunication	// obsługa połączenia z serwerem


### Moduł serwerowy: ###
pw.childcontrol.server
  * (Ar)(P)(3) pw.childcontrol.server.communication	// obsługa połączenia z klientem
  * (M)(G) (3) pw.childcontrol.server.childmanager	// obsługa dzieci przez rodziców
  * (M)(G) (3) pw.childcontrol.server.childsecurity	// obsługa wkroczenia na zagrożony teren
  * (M)(G) (3) pw.childcontorl.server.statistics		// statystyki
  * (Ar)(P)(3) pw.childcontrol.server.email		// obsługa maili
  * (Ar)(P)(3) pw.childcontrol.server.register		// obsługa rejestracji
  * (M)(G) (3) pw.childcontrol.server.database 		// obsługa bazy danych
  * (M)(G) (3) pw.childcontrol.server.dataanalyser	// analizowanie przesyłanych informacji do 	 							// i z klienta/serwera


### Moduł administracyjny ###
  * (A)(4) pw.childcontrol.administrator
  * (A)(4) pw.childcontrol.administrator.usercontrol		// zarządzanie użytkownikami



### Moduł obsługi mapy: ###
pw.childcontrol.mapmanager
  * (Ar)(3) pw.childcontrol.mapmanager.geopoint		// zarządzanie punktami


### Moduł GUI ###
pw.childcontrol.gui

  * (M)(P)(3) pw.childcontrol.gui.parent.server.www.childmanager
  * (Mi)(1) pw.childcontrol.gui.parent.server.phone.childmanager

  * (Ar)(P)(1) pw.childcontrol.gui.parent.map			// tworzenie widoku elementów mapy
  * (Mi)(1) pw.childcontrol.gui.parent.alarm
  * (Mi)(1) pw.childcontrol.gui.parent.register

  * (Mi)(2) pw.childcontrol.gui.child.alarm

  * (M)(P)(3) pw.childcontrol.gui.administrator.server.www.childmanager	// widok interfejsu
// admina			// z poziomu www
  * (Mi)(3) pw.childcontrol.gui.administrator.server.phone.childmanager


### Moduł GPS i BTS ###
  * (A)(P)(1) pw.childcontrol.parent.localisationmodule
  * (A)(P)(2) pw.childcontrol.child.localisationmodule

  * (A)(P)(1) pw.childcontrol.parent.localisationmodule.gps
  * (A)(P)(2) pw.childcontrol.child.localisationmodule.bts