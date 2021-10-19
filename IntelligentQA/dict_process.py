import csv
with open('userdict.txt','w+',newline='')as f:
    writer=csv.writer(f,delimiter=' ')
    filename=['nnb','nng','nnr','nnv']
    for name in filename:
        writer.writerow([name,100000,name])
        with open('raw_dict_data/'+name+'.csv','r',newline='') as raw:
            reader=csv.reader(raw)
            for row in reader:
                writer.writerow([row[1],len(row[1])*5000,name])