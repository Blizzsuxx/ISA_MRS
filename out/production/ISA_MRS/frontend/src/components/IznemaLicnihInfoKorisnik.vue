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
        <span>Sulejman Velicanstveni</span> <!--TODO: ovde dode ime korisnika-->
      </el-header>

      <el-main>
        <el-form ref="form" :model="form" label-width="120px">
  
          <el-form-item label="Ime">
    <el-input v-model="form.ime"></el-input>
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
    <el-button type="primary" @click="onSubmit">Sacuvaj</el-button>
    <el-button>Nemoj sacuvati</el-button>
  </el-form-item>
</el-form>
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

  export default {
    name: 'IzmenaLicnihInfoKorisnika',
    mounted(){
      //pozivanje ucitavanja podataka apoteka
      this.$store.dispatch("IznemaLicnihInfoKorisnik/dobaviProfil")
      console.log(this.form.ime)
    },
    methods: {
       onSubmit() {
        console.log('submit!');
      }
    },
        data() {
      return {
        form: {
          ime: '',
          prezime: '',
          zemlja: '',
          mesto: '',
          ulica: '',
          broj: '45',
          date1: '',
          type: [],
          resource: '' //pol
          
        },

        backup: {
          ime: '',
          prezime: '',
          zemlja: '',
          mesto: '',
          ulica: '',
          broj: '',
          date1: '',
          type: [],
          resource: '' //pol
          
        }
      
      };
     
    }
  };
</script>