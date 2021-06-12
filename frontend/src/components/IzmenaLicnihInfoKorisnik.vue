<template>
<el-main>
  <NavMeniZaPacijenta/>
    <el-container>
      <el-main>
        <el-button
                  size="mini"
                  type="info"
                  @click="handleInfo()">Omoguci menjanje</el-button>
        <el-form ref="form" :model="form" label-width="120px">
  
          <el-form-item label="Ime">
    <el-input v-model="form.ime" :disabled="onemoguci"></el-input>
  </el-form-item>

<el-form-item label="E-mail">
    <el-input v-model="form.email" :disabled=true></el-input>
  </el-form-item>

<el-form-item label="Prezime">
    <el-input v-model="form.prezime" :disabled="onemoguci"></el-input>
  </el-form-item>

<el-form-item label="Zemlja">
    <el-input v-model="form.zemlja" :disabled="onemoguci"></el-input>
  </el-form-item>

<el-form-item label="Mesto">
    <el-input v-model="form.mesto" :disabled="onemoguci"></el-input>
  </el-form-item>

  <el-form-item label="Ulica">
    <el-input v-model="form.ulica" :disabled="onemoguci"></el-input>
  </el-form-item>

  <el-form-item label="Broj">
    <el-input v-model="form.broj" :disabled="onemoguci"></el-input>
    
  </el-form-item>


  <el-form-item label="Datum rodjenja">
    <el-col :span="11">
      <el-date-picker type="date" placeholder="Izaberi dan" v-model="form.date1" style="width: 100%;"></el-date-picker>
    </el-col>
  </el-form-item>
  
  
  <el-form-item>
    <el-button  @click="onSubmit">Sacuvaj</el-button>
    <el-button  @click="vrati()">Nemoj sacuvati</el-button>
  </el-form-item>
</el-form>
      <Alergije/>
    <LekoviBezAlergija/>
      </el-main>
    </el-container>
  </el-main> 

</template>
<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>

<script>
import Alergije from './Alergije.vue'; 
import NavMeniZaPacijenta from './NavMeniZaPacijenta.vue';  
import LekoviBezAlergija from './LekoviBezAlergija.vue';

  export default {
    
  components: { Alergije, LekoviBezAlergija,NavMeniZaPacijenta},
    name: 'IzmenaLicnihInfoKorisnika',
    async mounted(){
      //pozivanje ucitavanja podataka apoteka
      
     await  this.$store.dispatch("IzmenaLicnihInfoKorisnik/dobaviProfil")
      
      
      this.form.ime=this.$store.state.IzmenaLicnihInfoKorisnik.korisnik.firstName
      this.form.prezime=this.$store.state.IzmenaLicnihInfoKorisnik.korisnik.lastName
      this.form.mesto=this.$store.state.IzmenaLicnihInfoKorisnik.korisnik.adresa.mesto
      this.form.ulica=this.$store.state.IzmenaLicnihInfoKorisnik.korisnik.adresa.ulica
      this.form.broj=this.$store.state.IzmenaLicnihInfoKorisnik.korisnik.adresa.broj
      this.form.email=this.$store.state.IzmenaLicnihInfoKorisnik.korisnik.email
      this.backup.email=this.$store.state.IzmenaLicnihInfoKorisnik.korisnik.email
      this.backup.ime=this.$store.state.IzmenaLicnihInfoKorisnik.korisnik.firstName
      this.backup.prezime=this.$store.state.IzmenaLicnihInfoKorisnik.korisnik.lastName
      this.backup.mesto=this.$store.state.IzmenaLicnihInfoKorisnik.korisnik.adresa.mesto
      this.backup.ulica=this.$store.state.IzmenaLicnihInfoKorisnik.korisnik.adresa.ulica
      this.backup.broj=this.$store.state.IzmenaLicnihInfoKorisnik.korisnik.adresa.broj

      this.$store.dispatch("Alergije/dobaviAlergije")
    },
    
    methods: {
      handleInfo() {
        
        this.onemoguci=false;},
       onSubmit() {
         if(this.onemoguci){confirm("Trenutno niste odabrali omogucavanje promena. Kliknite na dugme omoguci promene radi menjanja informacija.");}
        else{this.form.email=this.backup.email
        if(confirm("Unesene promene ce se zapamtiti sem mejla.")){
          console.log(this.form.resource);
        this.$store.dispatch("IzmenaLicnihInfoKorisnik/izmeniPodatke",this.form)
        this.backup.ime=this.form.ime
        this.backup.prezime=this.form.prezime
        this.backup.mesto=this.form.mesto
        this.backup.ulica = this.form.ulica
        this.backup.broj = this.form.broj
        this.backup.date1 = this.form.date1
        this.backup.resource = this.form.resource
        this.backup.email=this.form.email  }
        }
       

      },
       vrati() {
        this.form.ime=this.backup.ime
        this.form.prezime=this.backup.prezime
        this.form.mesto=this.backup.mesto
        this.form.ulica = this.backup.ulica
        this.form.broj = this.backup.broj
        this.form.date1 = this.backup.date1
        this.form.resource = this.backup.resource
        this.form.email=this.backup.email  
      }
    },
        data() {
      return {
        onemoguci: true,
        form: {
          ime: '' ,
          email: '' ,
          prezime: '',
          zemlja: '',
          mesto: '',
          ulica: '',
          broj: null,
          date1: '',
          resource: "" //pol
          
        },
        
        backup: {
          ime: '' ,
          prezime: '',
          email: '' ,
          zemlja: '',
          mesto: '',
          ulica: '',
          broj: null,
          date1: '',
          
          resource: "" //pol
          
        }
      
      };
     
    }
  };
</script>