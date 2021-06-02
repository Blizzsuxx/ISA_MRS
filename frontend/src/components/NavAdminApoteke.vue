<template>
    <el-menu :default-active="activeIndex" 
     class="el-menu-demo" mode="horizontal" @select="handleSelect"
     background-color="#409EFF"
    text-color="#fff"
    active-text-color="#D6E3F1">
  <el-menu-item index="1"><router-link to="/profilApoteke">Profil apoteke</router-link></el-menu-item>
  <el-submenu index="2"><template #title>Narudzbenice</template>
  <el-menu-item index="2-1"><router-link to="/kreiranjeNarudzbenice">Kreiraj narudzbenicu</router-link></el-menu-item>
  <el-menu-item index="2-2"><router-link to="/prikazNarudzbenica">Istorija narudzbenica</router-link></el-menu-item>
  </el-submenu>
  <el-submenu index="3">
  <template #title>Zaposleni</template>
  <el-menu-item index="3-1"><router-link to="/pregledDermatologa">Pregled zaposlenih dermatologa</router-link></el-menu-item>
  <el-menu-item index="3-2"><router-link to="/pregledFarmaceuta">Pregled zaposlenih farmaceuta</router-link></el-menu-item>
  <el-menu-item index="3-3"><router-link to="/zaposljavanjeDermatologa">Zaposljavanje dermatologa</router-link></el-menu-item>
  <el-menu-item index="3-4"><router-link to="/zaposljavanjeFarmaceuta">Zaposljavanje farmaceuta</router-link></el-menu-item>
  <el-menu-item index="3-5"><router-link to="/slobodanTermin">Kreiranje slobodnih termina</router-link></el-menu-item>
  </el-submenu>
  <el-menu-item index="4"><router-link to="/ap/lijekovi">Lijekovi apoteke</router-link></el-menu-item>
  <el-menu-item index="5"><router-link to="/poruceni">Hitni lijekovi</router-link></el-menu-item>
  <el-menu-item index="6"><router-link to="/izvjestaji">Izvjestaji</router-link></el-menu-item>
  <el-menu-item index="7"><router-link to="/adminApoteke/promjenaLozinke">Resetovanje lozinke</router-link></el-menu-item>
  <el-menu-item index="8" @click="odjava">Odjava</el-menu-item>
</el-menu>
</template>


<script>

export default {
    name: 'NavAdminApoteke',
    data()  {
        return {
      
        }
        
    },
    mounted() {
        
    },
    methods: {
        
        odjava(){
            this.$store.dispatch('APKorisnici/logout');
            this.$router.push('/ap/prijava');
        },
     
      
    },
   
    
  }
</script>