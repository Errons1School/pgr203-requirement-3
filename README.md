# Innlevering 3: Deployment og database

## Oppgave

Dere skal videreføre Jetty-serveren fra innlevering 2 slik at den kan deployes på Azure.

Applikasjonen skal være som før og inneholde en React applikasjon og som benytter JAX-RS med Jersey for å behandle en GET request og en POST request. Applikasjonen skal være en webshop som kan registrere og liste varer i en nettbutikk. *Varene skal registres i SQL Server på Azure*

*Det er veldig viktig at dere inkluderer link til deres website på *.azurewebsites.net i README.md-fila*

## Innlevering

Dere skal jobbe i grupper på 4 eller 6. Dere kan benytte gruppene fra innlevering 2, finne gruppe selv eller be om å bli tildelt en gruppe. Hver gruppe skal internt fordele seg i par som skal levere sammen. Alle skal utføre code review for de andre parene. Code review skal utføres på Feedback-branchen.

Innleveringen skal leveres på Canvas med link til GitHub-repository. Begge skal levere i Canvas (med link til samme repository).

Når dere oppretter repository via Github Classroom linken på Canvas, vil det automatisk opprettes en pull request med navn "feedback". Hver par innenfor en gruppe skal utføre en code review på det andre parets kode på denne pull requesten.

## Krav til innlevering

* Programmet
  * [ ] En applikasjons som kjører på Azure: https://<fyll ut>.azurewebsites.net
  * [ ] Serveren skal være implementert i Jetty, med en React applikasjon, JAX-RS endepunkt og DAO-kode
  * [ ] En JUnit test som sjekker GET
  * [ ] En JUnit test som sjekker POST
  * [ ] En JUnit test som sjekker at DAO lagrer mot H2
  * [ ] En JUnit test som sjekker at title på index.html er korrekt (bygges fra Vite)
* Innlevering
  * [ ] Begge har levert link til Github Repository i Canvas
  * [ ] Koden er sjekket inn på et repository på https://github.com/kristiania-pgr209-2021
  * [ ] GitHub repository er private. Dere skal gi tilgang til de som gir code review
* Github
  * [ ] Dere har merget pull en pull request
  * [ ] Dere har mottatt code review fra medstudenter på feedback-pull request
  * [ ] `.idea`, `*.iml` og `target` er lagt til i .gitignore og ikke sjekket inn
  * [ ] Github Actions rapporterer at 100% av testene kjører grønt
* Kode
  * [ ] Koden skal være korrekt strukturert etter Maven sine standarder (koden skal ligge under `src/main/java` og tester under `src/test/java`)
  * [ ] Klassene skal ligge i Java packages
  * [ ] Klassenavn, pakkenavn, metodenavn og variabelnavn skal følge Java-konvensjoner når det gjelder små og store bokstaver
  * [ ] Koden skal være korrekt indentert
  * [ ] Dersom dere har valgt en stor oppgave behøver dere ikke løse hele. 6-7 tester er tilstrekkelig
* README-fil
  * [ ] Korrekt GitHub Actions status badge
  * [ ] Link til code review dere har gjort for medstudenter med minst én issue fra hver av dere
