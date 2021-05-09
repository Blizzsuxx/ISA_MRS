<template>
    <NavMeniZaPacijenta/>
    <p> Kategorija kojoj pripadate je <b>{{this.naziv}} </b>.<br>
    Sa tom kategorijom, ostvarujete popust <b>{{this.popust }} % </b> na svaku rezervaciju leka, kao i na savetovanje i pregled kod dermatologa.</p>
    <br><br>
    <p>Trenutno imate: <b>{{this.poeni}} </b> poena.</p> 



</template>
<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
export default  {
 components :{NavMeniZaPacijenta},
 data() { 
        return {
          naziv: "l",
          popust: "m",
          poeni: "s"
        }
    },
 name: 'PregledKategorija',
    async mounted(){
     this.$store.dispatch("APPacijenti/dobaviKategorijuPacijenta").then(()=>{
        this.naziv=this.$store.state.APPacijenti.kategorija[0].naziv;
        this.popust=this.$store.state.APPacijenti.kategorija[0].popust;
        this.poeni=this.$store.state.APPacijenti.kategorija[0].poeni;
     })
    
    },
};
</script>