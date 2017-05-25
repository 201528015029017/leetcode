fin = open("result", "r")
result = []
flag = 0
for line in fin:
	flag = 1 - flag;
	b = ""
	while len(b) != len(line):
		b = line
		line = line.replace("  "," ")	
	a = line.split("\t")
	if flag == 0:
		a[0] = a[0][:-1]
		result[-1].append(a[0])
		result[-1].append(a[3])
	else:
		result.append(a[:-1])
print result

import commands
import os

doc = commands.getoutput("ls").split("\n")
s = set(doc)
for c in result:
	if c[0] + "-" + c[1] in s:
		continue
	if c[1] not in s and c[0] + " " + c[1] not in s:
		print c[1], " not in s"
	elif c[1] in s:
 		origin = "".join(c[1].replace(" ","\ "))
		new = "".join((c[0] + "-" + c[1]).replace(" ","\ "))
		cmd = "mv"+" "+origin+" "+new
		print cmd
		os.system(cmd)
	else:
		origin = "".join((c[0] + " " + c[1]).replace(" ","\ "))
		new = "".join((c[0] + "-" + c[1]).replace(" ","\ "))
		cmd = "mv"+" "+origin+" "+new
		print cmd
		os.system(cmd)
