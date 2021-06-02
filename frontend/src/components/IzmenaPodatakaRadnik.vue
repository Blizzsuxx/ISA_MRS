<template>
  <el-container style="height: 600px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']">
        <el-link :href="'/ap/' + this.$store.state.APKorisnici.trenutnaRedirekcija">
        <el-menu-item index="1">Home</el-menu-item>
        </el-link>
        <el-submenu index="1">
          <template #title><i class="el-icon-menu"></i></template>
          <slot />
        </el-submenu>
        <el-submenu index="2">
          <template #title><i class="el-icon-setting"></i></template>
          <el-link href="/ap/izmena">
            <el-menu-item index="2-1">Profil</el-menu-item>
          </el-link>
          <el-link :href="'/ap/' + this.$store.state.APKorisnici.trenutnaRedirekcija + '/pacijenti'" v-if="this.radnik != null && this.radnik.promenioSifru">
          <el-menu-item index="2-2">Prethodni Klijenti</el-menu-item>
            </el-link>
          <el-menu-item index="2-3" @click="kliknut" v-if="this.radnik != null && this.radnik.promenioSifru">Zakazivanje Odmora</el-menu-item>
          <el-link href="/ap/prijava">
          <el-menu-item index="2-4" href="/ap/prijava">Odjava</el-menu-item>
          </el-link>
        </el-submenu>

      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <slot name="header-slot"></slot>
        
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <template #dropdown>
            <el-dropdown-menu>
                <el-dropdown-item>Profil</el-dropdown-item>
                <el-dropdown-item>Odjava</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <span>Sulejman Velicanstveni</span>
      </el-header>


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
    <el-input v-model="form.email" :disabled="onemoguci"></el-input>
  </el-form-item>

<el-form-item label="sifra">
    <el-input v-model="form.sifra" :disabled="onemoguci"></el-input>
  </el-form-item>

<el-form-item label="Prezime">
    <el-input v-model="form.prezime" :disabled="onemoguci"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button  @click="onSubmit">Sacuvaj</el-button>
    <el-button  @click="vrati()">Nemoj sacuvati</el-button>
  </el-form-item>
</el-form>
      <APGodisnjiOdmor  ref="prozor" />
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
  import APGodisnjiOdmor from './modal/APGodisnjiOdmor'
  export default {
    
  components: {APGodisnjiOdmor},
    name: 'IzmenaPodatakaRadnik',
    async mounted(){
      //pozivanje ucitavanja podataka apoteka
      
      
      await this.$store.dispatch('APKorisnici/trenutniRadnik');
      await this.$store.dispatch("APPosete/dobaviPoseteAktivne");
      await this.$store.dispatch("APKorisnici/promeniRedirekciju", "dermatolog");
      this.radnik = this.$store.state.APKorisnici.trenutniRadnik;
      
      
      this.form.ime=this.$store.state.APKorisnici.trenutniRadnik.ime
      this.form.prezime=this.$store.state.APKorisnici.trenutniRadnik.prezime
      this.form.email=this.$store.state.APKorisnici.trenutniRadnik.mejl
      this.form.sifra = this.$store.state.APKorisnici.trenutniRadnik.sifra
      this.backup.email=this.$store.state.APKorisnici.trenutniRadnik.mejl
      this.backup.ime=this.$store.state.APKorisnici.trenutniRadnik.ime
      this.backup.prezime=this.$store.state.APKorisnici.trenutniRadnik.prezime
      this.backup.sifra = this.$store.state.APKorisnici.trenutniRadnik.sifra
    },
    
    methods: {
        kliknut(){
        this.$refs.prozor.modalOpen = true;
        console.log("A")
      },
      handleInfo() {
        
        this.onemoguci=false;},
       onSubmit() {
         if(this.onemoguci){confirm("Trenutno niste odabrali omogucavanje promena. Kliknite na dugme omoguci promene radi menjanja informacija.");}
        else{this.form.email=this.backup.email
        if(confirm("Unesene promene ce se zapamtiti sem mejla.")){
        this.$store.dispatch("IzmenaLicnihInfoKorisnik/izmeniPodatkeRadnik",this.form)
        this.backup.ime=this.form.ime
        this.backup.prezime=this.form.prezime
        this.backup.email=this.form.email  
        this.backup.sifra = this.form.sifra
        this.$router.push({ name: 'Prijava' })
        }
        }
        //this.$alert("Unesene promene ce se zapamtiti sem mejla.");

      },
       vrati() {
        this.form.ime=this.backup.ime
        this.form.prezime=this.backup.prezime
        this.form.email=this.backup.email  
        this.form.sifra = this.backup.sifra
      }
    },
        data() {
      return {
        onemoguci: true,
        form: {
          ime: '' ,
          email: '' ,
          prezime: '',
          sifra: '',
          radnik : this.$store.state.APKorisnici.trenutniRadnik,
          
        },
        
        backup: {
          ime: '' ,
          prezime: '',
          email: '' ,
          sifra: '',
          radnik : this.$store.state.APKorisnici.trenutniRadnik,
        }
      
      };
     
    }
  };
</script>