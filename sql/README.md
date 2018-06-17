# SQL Basic

## How to start the server
```bash
mysql-ctl start
mysql -u root
```

## How to select a database, and run a `.sql` file
1. Move to the directory with the `.sql` file
```bash
cd ~/sql/ThreeSeasonsHotel # Move to a directory

ls # List directory
# if you see your .sql file here, it'll work

mysql -u root # Open mySQL as root user.
```

```sql
USE tablename; -- Select a database
SOURCE filename.sql; -- Open the file
```
