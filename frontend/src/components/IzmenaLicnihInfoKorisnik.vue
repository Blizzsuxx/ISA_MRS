<template>
 <el-container style="height: 600px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']">
        <el-menu-item index="1">Home</el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <slot name="header-slot"></slot>
       
      </el-header>

      <el-main>
        <el-form ref="form" :model="form" label-width="120px">
  
          <el-form-item label="Ime">
    <el-input v-model="form.ime"></el-input>
  </el-form-item>

<el-form-item label="E-mail">
    <el-input v-model="form.email"></el-input>
  </el-form-item>

<el-form-item label="Prezime">
    <el-input v-model="form.prezime"></el-input>
  </el-form-item>

<el-form-item label="Zemlja">
    <el-input v-model="form.zemlja"></el-input>
  </el-form-item>

<el-form-item label="Mesto">
    <el-input v-model="form.mesto"></el-input>
  </el-form-item>

  <el-form-item label="Ulica">
    <el-input v-model="form.ulica"></el-input>
  </el-form-item>

  <el-form-item label="Broj">
    <el-input v-model="form.broj"></el-input>
    
  </el-form-item>


  <el-form-item label="Datum rodjenja">
    <el-col :span="11">
      <el-date-picker type="date" placeholder="Izaberi dan" v-model="form.date1" style="width: 100%;"></el-date-picker>
    </el-col>
  </el-form-item>
  
  <el-form-item label="Pol">
    <el-radio-group v-model="form.resource">
      <el-radio label="Musko"></el-radio>
      <el-radio label="Zensko"></el-radio>
    </el-radio-group>
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
  </el-container> 

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
import Alergije from './Alergije.vue';  //CroasOrigin !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
import LekoviBezAlergija from './LekoviBezAlergija.vue';

  export default {
    
  components: { Alergije, LekoviBezAlergija},
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
       onSubmit() {
        this.form.email=this.backup.email
        if(confirm("Unesene promene ce se zapamtiti sem mejla.")){
        this.$store.dispatch("IzmenaLicnihInfoKorisnik/izmeniPodatke",this.form)}
        //this.$alert("Unesene promene ce se zapamtiti sem mejla.");
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