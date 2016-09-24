# Minotaur 1369 (2015 - 2016) #

## Abstract ##
This repository is a collection of two code bases, one being the Palmetto code base and the World code base. The palmetto code base is code that was used during the Palmetto regional and does not reflect changes made during Orlando and later on during Worlds.


**Important Note**: The Palmetto code base currently contains PID Control code (currently only implementing a P control loop) that was not present during the Palmetto regional. Do to some issues, I had to test the Robot (9/19/16) using the outdated code base and is the reason for the inclusion of that code base.

## Palmetto ##
This is the version of the code base used during Palmetto. Missing out on control changes and Blocker subsystem. 

## World ##
This is the version of the code base that was pushed post-worlds. This contains code for gyro turns (no PID control), encoder movement, and potentiometer base. Will need to sanitize the code base and remove anything that doesn't work (or deprecated) and add PID control to turning and lift movements. Lift movement needs a rewrite. 

## Other News ##
There is a simple library that was created over the summer that, in theory, should simplify the creation of a robot. Robot code will be tested during a meeting and the existing code base can be ported over shortly. 
