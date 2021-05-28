<template>
    <el-menu :default-active="activeIndex" 
     class="el-menu-demo" mode="horizontal" @open="handleOpen" @close="handleClose"
     background-color="#409EFF"
    text-color="#fff"
    active-text-color="#D6E3F1">  
        <el-submenu index="1" v-if='potvrda'>
            <template #title>
            <i></i>
            <span>Ponude</span>
            </template>
            <el-menu-item index="1-1" @click="mojePonude">Moje Ponude</el-menu-item>
            <el-menu-item index="1-2" @click="narudzbenice">Narudzbenice</el-menu-item>
            <el-menu-item index="1-3" @click="stanjeLijekova">Lijekovi na Stanju</el-menu-item>
        </el-submenu>
        <el-submenu index="2">
            <template #title>
            <i></i>
            <span>Dodatne Opcije</span>
            </template>
            <el-menu-item v-if='potvrda' index="2-1" @click="infoNaloga">Nalog</el-menu-item>
            <el-menu-item index="2-2" @click="odjava">Odjava</el-menu-item>
        </el-submenu>
    </el-menu>
    <el-main>
        <div v-if="indikator == 1">
            <PotvrdaLozinke :izmjeniPotvrdu="izmjeniPotvrdu"/>
        </div>
        <div v-if="indikator == 2">
            <Nalog/>
        </div>
        <div v-if="indikator == 3">
            <DobavljacPonude/>
        </div>
        <div v-if="indikator == 4">
            <DobavljacLijekovi/>
        </div>
        <div v-if="indikator == 5">
            <DobavljacNarudzbenice/>
        </div>
        <div v-else>
        </div>
    </el-main>
</template>

<script>
import PotvrdaLozinke from './PotvrdaLozinke'
import Nalog from './Nalog'
import DobavljacPonude from './DobavljacPonude'
import DobavljacLijekovi from './DobavljacLijekovi'
import DobavljacNarudzbenice from './DobavljacNarudzbenice'

export default {
    name: 'Dobavljac',
    data()  {
        return {
          indikator: 0,
          potvrda: false,  
        }
        
    },
    mounted() {
        this.$store.dispatch("APKorisnici/potvrdaLozinke").
        then(response => {
            if (!response){
                this.indikator = 1;
                this.open3();
            } else {
                this.potvrda = true;
            }
        });
    },
    methods: {
        open1() {
        this.$message({
          showClose: true,
          message: 'Uspjesno izmjenjena lozinka.',
          type: 'success'
        });
        },
        open2() {
        this.$message({
          showClose: true,
          message: 'Podaci nisu validni.',
          type: 'error'
        });
        },
        open3() {
        this.$message({
          showClose: true,
          message: 'Prilikom prve primjene lozinku je potrebno izmjeniti.',
          type: 'info'
        });
        },
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        }, 
        odjava(){
            this.$store.dispatch('APKorisnici/logout');
            this.$router.push('/ap/prijava');
        },
        izmjeniIndikator(broj, nesto){
            this.indikator = broj;
            if (this.indikator === 1){
                this.promjeniti(nesto);
            } else if (this.indikator === 2){
                this.dobaviLijekove();
            } else if (this.indikator === 7){
                this.dobaviApoteke();
            }
        },
        izmjeniPotvrdu(izmjena){
            this.potvrda = izmjena;
            if(this.potvrda){
                this.indikator = 0;
                this.open1();
            } else {
                this.open2();
            }
        },
        infoNaloga(){
            this.indikator = 2;
        },
        mojePonude(){
            this.indikator = 3;
        },
        stanjeLijekova(){
            this.indikator = 4;
        },
        narudzbenice(){
            this.indikator = 5;
        }

    },
    components:{
        PotvrdaLozinke, Nalog, DobavljacPonude, DobavljacLijekovi, DobavljacNarudzbenice
    },
    
  }
</script>