<template>
  <el-table
    :ref="referenca"
    height="250"
    :data="narudzbenice"
    style="width: 100%"
  >
    <el-table-column property="id" label="Sifra"> </el-table-column>
    <el-table-column property="brojPonuda" label="Broj ponuda">
    </el-table-column>
    <el-table-column property="rok" label="Rok"> </el-table-column>
    <el-table-column property="zavrsena" label="Zavrsena" :formatter="formatirajZavrsenost"> </el-table-column>
  </el-table>
  <div style="margin-top: 20px"></div>
</template>

<script>
export default {
  name: "NarudzbeniceTabela",
  data() {
    return {};
  },
  props: ["narudzbenice", "referenca"],
  methods: {
    formatirajZavrsenost(row) {
      if (row.zavrsena) {
        return "Zavrsena";
      } else {
        return "Nije zavrsena";
      }
    },
  },
};
</script>
