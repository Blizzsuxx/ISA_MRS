<template>
<el-menu :default-active="activeIndex" 
     class="el-menu-demo" mode="horizontal" @select="handleSelect"
     background-color="#409EFF"
    text-color="#fff"
    active-text-color="#D6E3F1">
  <el-menu-item index="1">Pregled apoteka</el-menu-item>
  <el-submenu index="2">
    <template #title>Posete</template>
    <el-menu-item index="2-1">Istorija poseta dermatologu</el-menu-item>
    <el-menu-item index="2-2">Istorija poseta farmaceutu</el-menu-item>
    <el-menu-item index="2-3">Zakazane posete dermatolozima</el-menu-item>
    <el-menu-item index="2-4">Zakazane posete farmaceutima</el-menu-item>
    <el-menu-item index="2-5">Zakazivanje kod dermatologa</el-menu-item>
    <el-menu-item index="2-6">Zakazivanje kod farmaceuta</el-menu-item>
    
  </el-submenu>

  <el-submenu index="3">
    <template #title>Lekovi</template>
    <el-menu-item index="3-1">eRecepti</el-menu-item>
    <el-menu-item index="3-2">Rezervisani lekovi</el-menu-item>
    <el-menu-item index="3-3">Izdati lekovi</el-menu-item>
  </el-submenu>

  <el-menu-item index="4">Uvid u penale</el-menu-item>
  <el-menu-item index="5">Promocije</el-menu-item>
  <el-menu-item index="6">Zalbe</el-menu-item>
  
  <el-menu-item index="7"><a href="https://www.ele.me" target="_blank">Profil</a></el-menu-item>
</el-menu>
  
</template>

<script>

</script>