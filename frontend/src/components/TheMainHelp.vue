<template>
    <div>
        <!-- make each div into a row flexbox // each will be a StepX button-->
        <router-link to="#" tag="div" class="d-flex flex-row justify-content-between m-3">
            <div>
                <img  class="image" src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"/>
            </div>
            <!-- make div into vertical flexbox // will contain StepX title and description -->
            <div class="d-flex flex-column">
                <h2>Step One</h2>
                <p>lorem ipsum</p>
            </div>
            <div>
                <img  class="image" src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"/>
            </div>
        </router-link>
        <router-link to="#" tag="div" class="d-flex flex-row justify-content-between m-3">
    
            <img  class="image" src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"/>

            <!-- make div into vertical flexbox // will contain StepX title and description -->
            <div class="d-flex flex-column">
                <h2>Step Two</h2>
                <p>lorem ipsum</p>
            </div>

            <img  class="image" src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"/>
    
        </router-link>
        <router-link to="#" tag="div" class="d-flex flex-row justify-content-between m-3">
    
            <img  class="image" src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"/>

            <!-- make div into vertical flexbox // will contain StepX title and description -->
            <div class="d-flex flex-column">
                <h2>Step Three</h2>
                <p>lorem ipsum</p>
            </div>

            <img  class="image" src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"/>
    
        </router-link>
        <router-link to="#" tag="div" class="d-flex flex-row justify-content-between m-3">
    
            <img  class="image" src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"/>

            <!-- make div into vertical flexbox // will contain StepX title and description -->
            <div class="d-flex flex-column">
                <h2>Step Four</h2>
                <p>lorem ipsum</p>
            </div>

            <img  class="image" src="https://www.wolflair.com/wp-content/uploads/2017/01/placeholder.jpg"/>
    
        </router-link>
    </div>
</template>

<script>
export default {
    name: "TheMainHelp"
    
}
</script>

<style scoped>

.image {
    height: 200px;
    width: 200px;
}

</style>

