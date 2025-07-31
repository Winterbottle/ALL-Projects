Python 3.10.7 (tags/v3.10.7:6cc6b13, Sep  5 2022, 14:08:36) [MSC v.1933 64 bit (AMD64)] on win32
Type "help", "copyright", "credits" or "license()" for more information.
>>> for hours in range (24):
...      tor minutes in range (60)
...          For seconds in range (60):
...              for milliseconds in range (60):
...                  print (hours, ":- minutes, ":" seconds, ":" milliseconds)
... 
SyntaxError: unterminated string literal (detected at line 5)
>>> for hours in range (24):
...      for minutes in range (60)
...          for seconds in range (60):
...              for milliseconds in range (60):
...                  print (hours, ":", minutes, ":" ,seconds, ":" ,milliseconds)
