<template>
    <el-menu :default-active="activeIndex" 
     class="el-menu-demo" mode="horizontal" @select="handleSelect"
     background-color="#409EFF"
    text-color="#fff"
    active-text-color="#D6E3F1">
  <el-menu-item index="1"><router-link to="/profilApoteke">Profil apoteke</router-link></el-menu-item>
  

  <el-menu-item index="2"><router-link to="/kreiranjeNarudzbenice">Kreiraj narudzbenicu</router-link></el-menu-item>
  <el-menu-item index="3"><router-link to="/ap/lijekovi">Lijekovi apoteke</router-link></el-menu-item>
  <el-menu-item index="4"><router-link to="/pregledDermatologa">Pregled zaposlenih dermatologa</router-link></el-menu-item>
  <el-menu-item index="4"><router-link to="/pregledFarmaceuta">Pregled zaposlenih farmaceuta</router-link></el-menu-item>
  <el-menu-item index="5"><router-link to="/slobodanTermin">Kreiranje slobodnih termina</router-link></el-menu-item>
</el-menu>
</template>