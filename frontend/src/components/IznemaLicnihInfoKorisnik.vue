<template>
    <form>
      <div style="margin-top: 20px float: left">
      <label for="Ime">Ime: </label>
      <input type="text" required>
      </div>
      <br>
      <div style="margin-top: 20px">
      <label for="Prezime">Prezime: </label>
      <input type="text" required >
      </div>
      <br>
      <div style="margin-top: 20px">
      <label for="mejl">Mejl: </label>
      <input type="text"  required>
      </div>
      <br>
      <div style="margin-top: 20px">
      <label for="Zemlja">Zemlja: </label>
      <input type="text"  required>
      </div>
      <br>
      <div style="margin-top: 20px">
      <label for="Adresa">Adresa: </label>
      <input type="text"  required>
      </div>
     <br>
     <div style="margin-top: 20px">
     <label for="Broj">Broj: </label>
      <input type="text"  required>
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
       profil:null
    }
  },
  methods : {
    
  }
}
</script>