# MobileProject-17--18


## Gebruikte api voor (bijna) alles van de boeken op te zoeken  
http://isbndb.com/api/v2/docs  
De key die we hiervoor gebruiken is -> VI15DHE7  

Voorbeeld:  
http://isbndb.com/api/v2/json/VI15DHE7/book/principles_of_solid_mechanics  

## Extra
Om de covers van de boeken op te halen moeten we eer de ISBN hebben (daarvoor gebruiken we bovenstaande api)  
om op een andere api de covers op te halen.  
https://openlibrary.org/dev/docs/api/books  
Hiervoor moet de ISBN 13 gebruikt worden  

Voorbeeld:  
https://openlibrary.org/api/books?bibkeys=ISBN:9780849303159&jscmd=data&format=json
