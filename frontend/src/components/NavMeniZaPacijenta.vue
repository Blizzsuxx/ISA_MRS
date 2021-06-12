<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
    background-color="#409EFF"
    text-color="#fff"
    active-text-color="#D6E3F1"
  >
    <el-menu-item index="1"
      ><router-link to="/ap/apoteke">Pregled apoteka</router-link></el-menu-item
    >
    <el-submenu index="2">
      <template #title>Posete</template>
      <el-menu-item index="2-1"
        ><router-link to="/ap/istorijaPosetaDermatologu"
          >Istorija poseta dermatologu</router-link
        ></el-menu-item
      >
      <el-menu-item index="2-2"
        ><router-link to="/ap/istorijaPosetaFarmaceut"
          >Istorija poseta farmaceutu</router-link
        ></el-menu-item
      >
      <el-menu-item index="2-3"
        ><router-link to="/ap/zakazanePoseteDermatologuPacijent"
          >Zakazane posete dermatolozima</router-link
        ></el-menu-item
      >
      <el-menu-item index="2-4"
        ><router-link to="/ap/zakazanePoseteFarmaceutuPacijent"
          >Zakazane posete farmaceutima</router-link
        ></el-menu-item
      >

      <el-menu-item index="2-5" @click="handleZakaziD">Zakazivanje kod dermatologa
      </el-menu-item>
      <el-menu-item index="2-6" @click="handleZakaziF">
          Zakazivanje kod farmaceuta
        </el-menu-item
      >
    </el-submenu>

    <el-submenu index="3">
      <template #title>Lekovi</template>
      <el-menu-item index="3-1"
        ><router-link to="/ap/recepti">eRecepti</router-link></el-menu-item
      >
      <el-menu-item index="3-2"
        ><router-link to="/ap/rezervacije"
          >Rezervisani lekovi</router-link
        ></el-menu-item
      >
      <el-menu-item index="3-3"
        ><router-link to="/ap/istorijaLekova"
          >Izdati lekovi preko erecepta</router-link
        ></el-menu-item
      >
      <el-menu-item index="3-4"
        ><router-link to="/ap/qrkod"
          >Izdati preko qr koda</router-link
        ></el-menu-item
      >
      <el-menu-item index="3-5"
         @click="handleRezLek"
          >Rezervisi lek
        </el-menu-item
      >
    </el-submenu>

    <el-menu-item index="4"
      ><router-link to="/ap/penali">Uvid u penale</router-link></el-menu-item
    >
    <el-menu-item index="5"
      ><router-link to="/ap/pretplata">Promocije</router-link></el-menu-item
    >
    <el-submenu index="6">
      <template #title>Zalbe</template>
      <el-menu-item index="6-1"
        ><router-link to="/ap/kreirajZalbu"
          >Kreiranje Zalbe</router-link
        ></el-menu-item
      >
      <el-menu-item index="6-2"
        ><router-link to="/ap/zalbeIstorija"
          >Istorija Zalbi</router-link
        ></el-menu-item
      >
    </el-submenu>
    <el-menu-item index="7"
      ><router-link to="/ap/oceni">Oceni</router-link></el-menu-item
    >
    <el-menu-item index="8"
      ><router-link to="/ap/profil">Profil</router-link></el-menu-item
    >
    <el-submenu index="9">
      <template #title>Dodatne Opcije</template>
      <el-menu-item index="8-1" @click="odjava">Odjava</el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>

export default {
    name: 'Pacijent',
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
       async handleZakaziD(){
         await this.$store.dispatch("APPacijenti/proveriPenale")
           if(this.$store.state.APPacijenti.dozvola){
             console.log("proslo");
             this.$router.push('/ap/zakaziD');
           }else{
            this.$message({
                type: 'danger',
                message: 'Trenutno imate 3 ili vise penala, i ne mozete da zakazujete posetu dermatologu, farmaceutu ili da rezervisete lek.'
              }); }


        },
         async handleZakaziF(){
         await this.$store.dispatch("APPacijenti/proveriPenale")
           if(this.$store.state.APPacijenti.dozvola){
             console.log("proslo");
             this.$router.push('/ap/zakaziF');
           }else{
             this.$message({
                type: 'danger',
                message: 'Trenutno imate 3 ili vise penala, i ne mozete da zakazujete posetu dermatologu, farmaceutu ili da rezervisete lek.'
              });
             }


        },
        async handleRezLek(){
          ///ap/rezervisanjeLekova'
           await this.$store.dispatch("APPacijenti/proveriPenale")
           if(this.$store.state.APPacijenti.dozvola){
             console.log("proslo");
             this.$router.push('/ap/rezervisanjeLekova');
           }else{
            this.$message({
                type: 'danger',
                message: 'Trenutno imate 3 ili vise penala, i ne mozete da zakazujete posetu dermatologu, farmaceutu ili da rezervisete lek.'
              });
            }
        }
        
      
    },
   
    
  }
</script>