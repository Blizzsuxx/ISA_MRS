<template>
    <form>
      <div style="margin-top: 20px float: left">
      <label for="Ime">Ime: </label>
      <input type="text" required v-model="profil1.ime">
      </div>
      <br>
      <div style="margin-top: 20px">
      <label for="Prezime">Prezime: </label>
      <input type="text" required v-model="profil1.prezime">
      </div>
      <br>
      <div style="margin-top: 20px">
      <label for="mejl">Mejl: </label>
      <input type="text"  required v-model="profil1.username">
      </div>
      <br>
      <div style="margin-top: 20px">
      <label for="Zemlja">Zemlja: </label>
      <input type="text"  required v-model="profil1.adresa">
      </div>
      <br>
      <div style="margin-top: 20px">
      <label for="Ulica">Ulica: </label>
      <input type="text"  required v-model="profil1.adresa">
      </div>
     <br>
     <div style="margin-top: 20px">
     <label for="Broj">Broj: </label>
      <input type="text"  required v-model="profil1.adresa">
     </div>
     <br>
    </form>
</template>

<script>
export default {
  name: 'IzmenaLicnihInfoKorisnika',
  mounted(){
      //pozivanje ucitavanja podataka apoteka
      this.$store.dispatch("IznemaLicnihInfoKorisnik/dobaviProfil")
     
    },
  data(){
    return {
       profil1:{},
       backup:[]
    }
  },
  methods : {
    

  

  }
}
</script>