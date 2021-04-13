<template>
  <el-container style="height: 600px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']">
        <el-link href="/ap/dermatolog">
        <el-menu-item index="1">Home</el-menu-item>
        </el-link>
        <el-submenu index="1">
          <template #title><i class="el-icon-menu"></i></template>
          <el-menu-item index="1-1">Zapocni Pregled</el-menu-item>
          <el-menu-item index="1-2">Zakazi Pregled</el-menu-item>
          <slot />
        </el-submenu>
        <el-submenu index="2">
          <template #title><i class="el-icon-setting"></i></template>
          <el-menu-item index="2-1">Profil</el-menu-item>
          <el-link href="/ap/dermatolog/pacijenti">
          <el-menu-item index="2-2">Prethodni Klijenti</el-menu-item>
            </el-link>
          <el-menu-item index="2-3">Zakazivanje Odmora</el-menu-item>
          <el-menu-item index="2-4">Odjava</el-menu-item>
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
          
        <h3>Pregled svih lijekova</h3> 
        
        <LijekoviTabela @promjenjena-selekcija="selektujRedove" ref='dijete' v-bind:lijekovi="$store.state.APlijekovi.sviLijekovi" referenca="multipleTable" />
        <div style="margin-top: 20px">
            <el-button type="primary" @click="ocistiSelekciju()" plain>Očisti selekciju</el-button>
            <el-button type="primary" @click="dodeliLekove()" plain>Dodeli Lekove</el-button>
            <el-popconfirm

        title="Da li ste sigurni da želite izbrisati lijek?"
        >
        <template #reference>
            <el-button type="primary" @click="zavrsiPregled()">Zavrsi pregled</el-button>
        </template>
        </el-popconfirm>
    
        </div>



        <el-input
            type="textarea"
            :rows="2"
            placeholder="Unesite informacije o pregledu ovde"
            v-model="textarea">
        </el-input>
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
import { defineComponent, ref } from 'vue'
import LijekoviTabela from  './LijekoviTabela'
  export default defineComponent ({
    name: 'APDermatolog',
    components : {
        LijekoviTabela,
    },
    mounted(){
        this.$store.dispatch("APlijekovi/dobaviLijekove")
    },
    setup() {
        return {
            
            textarea: ref('')
        }
    },


    methods: {

        ocistiSelekciju(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.dijete.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.dijete.$refs.multipleTable.clearSelection();
        }
      },
      dodeliLekove(){
        this.$store.dispatch("APlijekovi/promjeniStanje",this.$refs.dijete.multipleSelection)
        if(this.$store.state.APlijekovi.zabranjeni.length!=0){
        this.greska=true;
        this.poruka = `Lijekovi sa identifikatorima: ${this.$store.state.APlijekovi.zabranjeni.join(',')} ne mogu biti prebaceni u magacin jer
         se nalaze u nekoj od rezervacija pacijenata`;
        }
        else{
          this.greaska = false;
          
        }
        this.$refs.dijete.$refs.multipleTable.clearSelection();
      },
    
        selektujRedove(val) {
        this.$refs.dijete.multipleSelection = val;
      },

      zavrsiPregled(){

      }
        
    },
        data() {


      return {
        greska : false,
        prozor: false,
        modalOpen: false,
        poruka : "",
      }
    }
  });
</script>