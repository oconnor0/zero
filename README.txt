 |_  / _ \ '__/ _ \
  / /  __/ | | (_) |
 /___\___|_|  \___/
====================
(C) 2012 Matt O'Connor <thegreendragon@gmail.com>

Overview
--------

Zero v0.0.1.* [5] is a language & compiler design exploration for the JVM. It is inspired by MixML [1], Eff [2], Manticore [3], Named Type Class Instances [6], and others.

Building & Running
------------------

From the `zero-compiler` directory, run `mvn compile exec:java` to start the REPL. Once everything is built, `mvn exec:java` is sufficient to start it. There is currently no support for interpreting or loading files.

$ cd zero-compiler
$ mvn compile exec:java

Details
-------

The parser is a Pratt parser [4]. The intention is for the grammar to be extensible at some level, but for ease of implementation a static grammar has been implemented.

[1] http://www.mpi-sws.org/~rossberg/mixml/
[2] http://math.andrej.com/eff/
[3] http://manticore.cs.uchicago.edu/
[4] http://journal.stuffwithstuff.com/2011/03/19/pratt-parsers-expression-parsing-made-easy/
[5] Versioned via http://semver.org/
[6] http://www.cas.mcmaster.ca/~kahl/Publications/Conf/Kahl-Scheffczyk-2001.pdf
