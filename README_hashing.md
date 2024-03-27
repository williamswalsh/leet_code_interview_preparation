### Non-cryptographic hash methods
1) Speed
2) Should generate **uniformly distributed hashes** for sharding.
3) Resistance to collisions
4) Compliance with the "Avalanche Effect"  
   **Avalanche Effect:**  
   For a good hash f(x) a small change in the f(x) input results in a large change in the hash outputted.  
   i.e. if an input is changed slightly, the output changes significantly.

#### MurmurHash:  
This is a popular choice for hash functions because it's super fast, and it can handle any size of data.  
Just like a pro player who can find any hiding spot in record time!  

#### FNV Hash:  
Short for Fowler-Noll-Vo, FNV hash is another quick and efficient function.  
It's like a player who has mastered the art of the game and can find even the trickiest hiding spots.  

#### CityHash:  
This one's developed by Google. It's known for its speed and effectiveness when dealing with strings of data.  
Think of it as a player who's really good at finding people who are hiding in complex spots.  

#### Perl's hash function:  
This one's a part of the Perl programming language.   
It uses a unique approach to generate hash keys, much like a player who comes up with innovative ways to find the other players.


### Popular Java implementations of non-cryptographic hash functions:

- DJB2 (32-bit)
- SDBM (32-bit)
- LoseLose (32-bit)
- FNV-1/FNV-1a (32-bit)
- CRC16 (16-bit)
- Murmur2/Murmur3 (32-bit)


