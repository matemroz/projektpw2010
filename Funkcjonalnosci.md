# Funkcjonalność systemu Child Control #
## Funkcjonalności modułów ##


### Moduł dziecka ###
Funkcje podstawowe:
  * czytanie współrzędnych GPS
  * wysłanie danych
  * okresowe automatyczne wysyłanie danych z położeniem na serwer


Funkcje dodatkowe:
  * czytanie położenia z anten BTS
  * alarm
  * powiadomienie rodziców o zagrożeniu
  * jednoczesne powiadomienie grupy osób o sytuacji wyjątkowej


### Moduł rodzica ###
Funkcje podstawowe:
  * dodanie dziecka do kontroli
  * ustawienie terenów zagrożonych dla każdego dziecka
  * stworzenie domyślnych map z oznaczonym terenem zagrożeń (dla każdego   dziecka)
  * sprawdzenie położenia dziecka
  * automatyczne powiadamianie o wkroczeniu na zagrożony teren



Funkcje dodatkowe:
Wprowadzenie metryki “zdrowasiek” (20 s. = 1 zdrowaśka)
Napisanie: BitchVersion



### Moduł administratora = moduł internetowy ###
Funkcje podstawowe:

Funkcje dodatkowe:

http://www.bipper.no/
Scenariusze przypadków użycia

### Aplikacja dziecka ###

### Aplikacja rodzica ###
Przypadki użycia:
Sprawdzenie położenia dziecka:
  1. Rodzic włącza aplikacje
  1. Wybranie opcji “Lokalizacja dziecka” albo “Lokalizuj dziecko”
  1. System wyświetla listę dzieci
  1. Rodzic wybiera dziecko i zatwierdza
  1. Serwer zwraca dane geograficzne
  1. Aplikacja rodzica wyświetla mapę z zaznaczonym punktem i najbliższy adres odpowiadający położeniu dziecka.



Sprawdzenie położenia dziecka:
  1. (Rodzic) wybiera opcję "Sprawdź położenie dziecka"
  1. (System) wyświetla listę dzieci
  1. (Rodzic) wybiera dziecko z listy
  1. (System) wysyła zapytanie do serwera o położenie dziecka
  1. (System) wyświetla mapę z zacznaczonym punktem

Sprawdzenie historii położenia dziecka:
  1. (Rodzic) sprawdza opcję "Historia lokalizacji dziecka"
  1. (System) wyświetla listę dzieci
  1. (Rodzic) wybiera dziecko z listy
  1. (System) wyświetla opcje konfiguracji
  1. (Rodzic) wybiera ilość wyświetlanych punktów oraz zakres czasu
  1. (System) wysyła zapytanie do bazy
  1. (System) wyświetla mapę z zaznaczonymi punktami

Pokaż szczegóły markera:
  1. (Rodzic) wybiera marker
  1. (System) wyświetla tabelę historii

Dodanie podejrzanego miejsca:
  1. (Rodzic) wybiera opcję "Dodaj niebezpieczną lokalizację"
  1. (System) wyświetla listę dzieci
  1. (Rodzic) wybiera dziecko z listy
  1. (System) wyświetla listę opcji wprowadzania lokalizacji
  1. (Rodzic) wybiera jedną z opcji
  1. (Rodzic) wprowadza dane lokalizacji


Wyznaczenie terenu zagrożonego dla dziecka
  1. Rodzic wybiera opcję “Oznacz teren niebezpieczny”
  1. Rodzic wybiera dziecko listy.
  1. Aplikacja prezentuje mapę z możliwością zaznaczania markerów. W tym momencie punkty są łączone ze sobą, w celu wydzielenia obszaru zamkniętego. Klient po skończeniu dodawania punktów i zaznaczeniu obszaru zatwierdza wybór.
  1. Wyświetlany jest formularz, w którym wpisuje się: nazwę obszaru. Klient zatwierdza po
wpisaniu.
  1. System wyświetla listę zdefiniowanych obszarów i przypisanych do dziecka.

Automatyczne wykrywanie wejścia w niebezpieczny teren
  1. System odbiera dane o położeniu dziecka. W przypadku wykrycia kolizji położenia dziecka z  terenem niebezpiecznym, system podejmuje akcje.
  1. W systemie wyświetla się okno z informacją (alertem): dziecko, nazwa terenu zagrożonego, przycisk do mapy, gdzie jest zaznaczony marker z lokalizacją dziecka oraz zaznaczonym zagrożonym terenem.


Dodanie nowego dziecka (telefonu) do systemu śledzenia:
  1. (od teraz telefon dziecka) Instalacja aplikacji na telefonie dziecka
  1. Połączenie z systemem i zarejestrowanie numeru IMEI z telefonu dziecka
  1. System zwraca unikalny kod rejestracyjny i numer id klienta dziecka na telefon dziecka.
  1. (od teraz telefon rodzica) Rodzic wybiera ustawienia -> dodanie nowego dziecka:
  1. Rodzic uzupełnia podstawowe jego dane (imie, rok urodzenia) oraz wpisuje id klienta dziecka i kod rejestracyjny. Potwierdza, i dane lecą na serwer
  1. System odbiera i wysyła że jest gites.

Rejestracja nowego konta rodzica:
  1. Rodzic uruchamia aplikację
  1. Wybiera opcję  “Zarejestruj się”
  1. Rodzic wypełnia formularz (pole na imię, nazwisko, e-mail, login, hasło).
  1. Wysyła na serwer dane rejestracyjne. Dane do loginu: e-mail, haslo swoje
  1. system odpowiada “pozdro600”

Dodanie nowego dziecka:
  1. (Rodzic) wybiera opcję "Dodaj dziecko do systemu"
  1. (System) wyświetla formularz do wypełnienia
  1. (Rodzic) wypełnia formularz
  1. (System) przesyła formularz na serwer
  1. (System) prosi o podanie kodu wysłanego na telefon dziecka
  1. (Rodzic) wprowadza kod przesłany na telefon dziecka


Działanie aplikacji dziecka:
  1. Aplikacja się uruchamia z długiego snu
  1. Ściąga ustawienia co ile zdrowasiek ma wysyłać dane na serwer
  1. Ogarnia się i zaczyna normalną pracę (wysyłania danych gps, swojego id)
…....
  1. Co jakiś czas sprawdza czy dane konfiguracyjne (sprawdza booleana) się zmieniły

Słownik

Marker - reprezentuje punkt na mapie określający z lokalizacją
Tabela historii - zawiera: nr porządkowy, godzinę (datę), najbliższy adres, dane geograficzne
Lista opcji wprowadzania lokalizacji -
Zdrowaśka - 20 sekund
Formularz dodania dziecka -