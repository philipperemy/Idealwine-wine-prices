rm -rf screenshots/*; rm output.csv nohup.out; nohup python3 -u main.py & sleep 2; tail -f nohup.out
