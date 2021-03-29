<template>
  <el-container style="height: 600px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '3']">
        <el-menu-item index="1">Home</el-menu-item>
        <el-submenu index="1">
          <template #title><i class="el-icon-menu"></i></template>
          <el-menu-item index="1-1">Pregled apoteka</el-menu-item>
          <el-menu-item index="1-2">Istorija poseta dermatologu</el-menu-item>
         <el-menu-item index="1-3">Istorija poseta farmaceutu</el-menu-item>
         <el-menu-item index="1-4">Zakazane posete dermatolozima</el-menu-item>
         <el-menu-item index="1-5">Zakazane posete farmaceutima</el-menu-item>
         <el-menu-item index="1-6">eRecepti</el-menu-item>
         <el-menu-item index="1-7">Rezervisani lekovi</el-menu-item>
         <el-menu-item index="1-8">Uvid u penale</el-menu-item>
         <el-menu-item index="1-9">Izdati lekovi</el-menu-item>
         <el-menu-item index="1-10">Promocije</el-menu-item><!--TODO proveri ovo-->
         <el-menu-item index="1-11">Zalbe</el-menu-item>
         <el-menu-item index="1-12">Zakazivanje kod dermatologa</el-menu-item>
         <el-menu-item index="1-12">Zakazivanje kod farmaceuta</el-menu-item>
         <el-menu-item index="1-13">Profil</el-menu-item>
         
          <slot />
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <slot name="header-slot"></slot>
        
        <Selecter options="Nedeljno;Mesecno;Godisnje" style="align: left" @change="promena"></Selecter>

        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <template #dropdown>
            <el-dropdown-menu>
                <el-dropdown-item>Profil</el-dropdown-item>
                <el-dropdown-item>Odjava</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <span>Ime pacijenta</span>
      </el-header>

      <el-main>
        <el-table :data="tableData">
          <el-table-column prop="start" label="Pocinje" width="140">
          </el-table-column>
          <el-table-column prop="end" label="Zavrsetak" width="140">
          </el-table-column>
          <el-table-column prop="name" label="Ime" width="120">
          </el-table-column>
      <el-table-column prop="lastname" label="Prezime" width="120">
          </el-table-column>
          <el-table-column prop="pharmacy" label="Apoteka" width="240">
          </el-table-column>
        </el-table>
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
import Selecter from './Selecter.vue';
  export default {
    name: 'PocetnaStranaPacijenta',
    components : {
        Selecter,
    },

    methods: {
        promena(value){
            let time = 7;
            if(value === "Mesecno"){
                time = 30;
            }
            if(value === "Godisnje"){
                time = 365;
            }
            this.tableData = this.storageData.filter((item) => {
                return time >= ((new Date(item.end)).getTime() -  (new Date(item.start)).getTime()) / (1000 * 3600 * 24)} );
        }
    },
        data() {
      const item = {
        start: '05/28/2015 08:30',
        end: '10/28/2015 09:00',
		name: 'John',
		lastname: 'Titor',
        pharmacy: 'No. 189, Grove St, Los Angeles'
      };


      return {
        tableData: Array(20).fill(item),
        storageData: Array(20).fill(item),
      }
    }
  };
</script>