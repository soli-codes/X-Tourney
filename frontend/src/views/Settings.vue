<template>
  <div>
    <div>
      <label for="imageURL"
        >Profile Image URL:
        <input type="text" v-model="imageURL" />
        <button @click="saveNewImage">Save</button>
      </label>
    </div>
    <div><button @click="logout">Logout</button></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      imageURL: '',
    };
  },
  methods: {
    logout() {
      this.$store.commit('LOGOUT');
      this.$router.push('/help');
    },
    saveNewImage() {},
  },
};
</script>
