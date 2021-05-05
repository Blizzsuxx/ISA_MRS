<template>
    <el-menu :default-active="activeIndex" 
     class="el-menu-demo" mode="horizontal" @select="handleSelect"
     background-color="#409EFF"
    text-color="#fff"
    active-text-color="#D6E3F1">
  <el-menu-item index="1"><router-link to="/ap/apoteke">Pregled apoteka</router-link></el-menu-item>

  <el-menu-item index="2"><router-link to="/ap">Pregled lekova</router-link></el-menu-item>
  <el-menu-item index="3"><router-link to="/ap/registracija">Registracija</router-link></el-menu-item>
  <el-menu-item index="4"><router-link to="/ap/prijava">Prijavite se</router-link></el-menu-item>
  
  
</el-menu>
</template>