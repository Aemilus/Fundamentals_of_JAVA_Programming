#!/bin/bash

MYSQL_PWD=root..

echo "Erasing agenda database if any."
mysql -uroot --table < sql/erase_agenda_db.sql
mysql -uroot --table < sql/erase_agenda_user.sql
echo "Done."

exit 0
