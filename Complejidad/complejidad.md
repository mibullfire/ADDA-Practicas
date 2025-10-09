# Ejercicios de Complejidad

## Ejercicio 1

```java
for (int i=1; i<n; i++)
    r += a * 1;
```

Bucle donde se repite una operacion un número de $n$ veces.

$$\sum^n_{i=1}1 \cong n$$

## Ejercicio 2

```java
i=n;
while (i >= 1) {
    s = s + i;
    i = i / r;
}
```

*Completar*

## Ejercicio 4

```java
for (int i = 1; i <= n – 1; i++) { 
    for (int j = i + 1; j <= n; j++) { 
        for (int k = 1; k <= j; k++) { 
            r = r + 2; 
        } 
    } 
}
```

Aplicamos los sumatorios para cada uno de los bucles *for* con sus respectivas condiciones.

$$T(n)=\sum_{i=1}^n \left(\sum_{j = i+1}^n\left(\sum_{k=1}^j1\right)\right)$$

$$T(n)=\sum_{i=1}^n \left(\sum_{j = i+1}^nj\right)$$

Llegado a este paso, tenemos que solventar el problema de $\sum_{j = i+1}^nj$. Es un problema trivial, basta con plantear el sumatorio desde $j=1$, hasta $n$; y restarle el sumatorio de $j=1$, hasta $i$.

$$\sum_{j = i+1}^nj=\sum_{j = 1}^nj-\sum_{j = 1}^ij=\frac{1}{2}n^2-\frac{1}{2}i^2$$

Luego tendríamos:

$$T(n)=\sum_{i=1}^n \left(\frac{1}{2}(n^2-i^2)\right)=n^3-\frac{n^3}{3}=\frac{2}{3}n^3= n^3$$

## Ejercicio 12

```java
double f (int n, double a) { 
    double r; 
    if (n == 1) {  
        r = a; 
    } else { 
        r = f (n/2, a+1) – f (n/2, a–1); 
        for (int i = 1; i <= n; i++) { 
            r += a * i; 
        } 
    } 
    return r; 
} 
```

Esta función tiene dos parámetros de entrada, pero suponiendo que $n$ tiende a un valor cercano a infinito, la variable $a$ es completamente prescindible. Por lo que nos centramos en el tiempo que supone la variable $n$:

$$T(n) = T(n/2)+T(n/2)+\sum^n_{i=1}1=2 \cdot T \left( \frac{n}{2} \right)+n$$

Mirando la *Hoja de Ayuda*, tenemos que: $T(n)=aT(n/b)+n^d \log^pn$, siendo: $a=2$, $b=2$, $d=1$ y $p=0$. Como $a=b^d$, entonces:

$$T(n)=n^1log^{0+1}n=n\cdot \log n$$
