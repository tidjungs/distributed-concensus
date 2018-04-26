## Distributed Concensus

How to
- Connectivity probability ของ random graph: เช่น {.1, .2, .3}
- Probability ที่โหนดใดๆจะถูกกําหนดให้เป็น malicious: เช่น {.15, .30, .45}
- Probability ท่ี valid transaction แต่ละอันจะถูกสื่อสารไปยังโหนดอื่นๆ เช่น {.01, .05, .10}
- จํานวนรอบในการทํา simulation เช่น {10, 20}
```
javac *.java
java Simulation [Connectivity probability] [Malicious probability] [Probability valid transactions] [Round compute]
```
