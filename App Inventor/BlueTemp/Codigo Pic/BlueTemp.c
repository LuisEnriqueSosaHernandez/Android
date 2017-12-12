//***************************************************************************
//   DESCRIPCION DEL PROGRAMA:  Muestra la senal analogica en grados Celsius                                           
//    NOMBRE DEL ARCHIVO: BlueTemp.C
//    FECHA: 08 de diciembre del 2017                                                                          
//    AUTOR: Equipo 2                                                           
//    CATEDRATICO: ING. CARLOS ROBERTO GONZALEZ ESCARPETA              
//    ASIGNATURA: SISTEMAS PROGRAMABLES                                   
//***************************************************************************
//              INSTITUTO TECNOLOGICO DE VERACRUZ
//***************************************************************************
//OBJETIVO:Analizar el modulo ADC de un microcontrolador para convertir una 
//senal analogica a digital
//*********************************CODIGO*******************************
#include <16f877a.h>
#FUSES NOWDT, XT, NOPUT, NOPROTECT, BROWNOUT, NOLVP, NOCPD, NOWRT
#device adc=10
#use delay(clock=4000000)       
#use RS232(BAUD=9600,BITS=8,PARITY=N,XMIT=PIN_C6,RCV=PIN_C7)
#use standard_io(d)
int16 ADC;
float voltaje,temp;
char valor;
#int_rda
void serial_isr(){
valor=getc();//recibe el dato del pc y lo guarda en valor
}
void main()
{
enable_interrupts(global);
//Configuración módulo analógico
setup_adc_ports(AN0);
setup_adc(ADC_CLOCK_INTERNAL);            
set_adc_channel(0);
while(true) 
{
     ADC=read_adc();
     voltaje=(ADC*5.0)/1023.0;
     temp= voltaje*100.0; 
      printf("\r%3.2f \n",temp);    
     delay_ms(3000); 
   } // fin del ciclo while
} // fin del main
    
