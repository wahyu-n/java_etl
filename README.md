## Java ETL
ETL menggunakan Java untuk mengolah data pendidikan

### Requirement
- Docker
- Java

### How to run
- Build Postgres image : `docker build -t postgres`
- Run Postgres container : `docker run -p 127.0.0.1:5432:5432 -- name db_postgres -i -t postgres`

### Explanation
ETL untuk mengolah `data_pendidikan.csv` menggunakan bahasa pemrograman Java, kemudian data disimpan ke dalam database PostgreSQL yang di jalankan di virtual machine Docker. 
