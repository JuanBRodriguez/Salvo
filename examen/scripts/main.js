var A = [];
var B = [];
var C = [];
var J = 0;
var N = 0;
var M = 0;

//Asignacion de ejemplos
A[0] = 1;
B[0] = 4;
A[1] = 4;
B[1] = 5;
A[2] = 5;
B[2] = 9;
A[3] = 8;
B[3] = 10;

C[0] = 4;
C[1] = 6;
C[2] = 0;
C[3] = 10;
C[4] = 2;

N = A.length;
M = C.length;

//ejecucion de ejemplo
J = solucion(A, B, N, C, M);
//resultado
console.log("numero de clavos minimo: "+ J);

function solucion(a, b, n, c, m) {
    let j = 0;
    let clavados = [];
    let hechos = [];

    // itaracion de los clavos
    for (let i = 0; i < m; i++) {
        let verificador = 0;
        clavados[i] = [];
        // ve a que tablon puede clavar cada clavo
        for (let k = 0; k < n; k++) {
            if (a[k] <= c[i] & c[i] <= b[k])
                clavados[i].push(k);
        }
        console.log("clavo N°" + i + " clava al tablon: " + clavados[i]);
        // itero los tablones que clavo el clavo seleccionado para guardarlos
        for (let q = 0; q < clavados[i].length; q++) {
            hechos.push(clavados[i][q]);
            //elimino id repedidos del array de tabloques ya clavados
            hechos = unique(hechos);
            //ordeno el array de tabloques ya clavados
            hechos.sort(function (a, b) {
                return a - b;
            });
            console.log(hechos);
        }
        //se compara los id de tablones clavados con la lista de tablones
        for (let k = 0; k < n; k++) {
            if (hechos[k] == k) {
                verificador++;
            }
        }
        /*si el numero de ids correctos es igual 
         *a el numero de ids total devulve la
         *cantidad minima de clavos
        */
        if (verificador == n) {
            j = verificador;
        } else {
            j = -1
        }
    }
    return j;
}
//funcion para eliminar repetidos
function unique(arrayZ) {
    return arrayZ.filter((valor, indiceActual, arreglo) => arreglo.indexOf(valor) === indiceActual);
}