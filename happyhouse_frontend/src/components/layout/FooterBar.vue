<template>
  <footer
    id="footer"
    class="overflow-hidden position-relative bg-dark text-white mt-5"
  >
    <!--Divider-->
    <svg
      class="position-absolute start-0 bottom-0 text-gray-800 h-40"
      preserveAspectRatio="none"
      width="100%"
      height="250"
      viewBox="0 0 1200 250"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        opacity="0.1"
        fill-rule="evenodd"
        clip-rule="evenodd"
        d="M0 62.5L22 46.875C44 31.25 89 0 133 0C178 0 222 31.25 267 72.9167C311 114.583 356 166.667 400 187.5C444 208.333 489 197.917 533 182.292C578 166.667 622 145.833 667 114.583C711 83.3333 756 41.6667 800 26.0417C844 10.4167 889 20.8333 933 62.5C978 104.167 1022 177.083 1067 208.333C1111 239.583 1156 229.167 1178 223.958L1200 218.75V250H1178C1156 250 1111 250 1067 250C1022 250 978 250 933 250C889 250 844 250 800 250C756 250 711 250 667 250C622 250 578 250 533 250C489 250 444 250 400 250C356 250 311 250 267 250C222 250 178 250 133 250C89 250 44 250 22 250H0V62.5Z"
        fill="currentColor"
      />
      <path
        opacity="0.1"
        fill-rule="evenodd"
        clip-rule="evenodd"
        d="M0 93.667L24.8889 87.4756C49.7778 82.8321 99.5556 71.9971 149.333 93.667C200.889 113.789 250.667 166.416 300.444 186.538C350.222 208.208 400 197.373 449.778 171.06C499.556 144.746 549.333 102.954 600.889 76.6407C650.667 50.3272 700.444 41.0401 750.222 61.1622C800 82.8321 849.778 135.459 899.556 129.268C949.333 124.624 1000.89 61.1622 1050.67 50.3272C1100.44 41.0401 1150.22 82.8321 1175.11 102.954L1200 124.624V250H1175.11C1150.22 250 1100.44 250 1050.67 250C1000.89 250 949.333 250 899.556 250C849.778 250 800 250 750.222 250C700.444 250 650.667 250 600.889 250C549.333 250 499.556 250 449.778 250C400 250 350.222 250 300.444 250C250.667 250 200.889 250 149.333 250C99.5556 250 49.7778 250 24.8889 250H0V93.667Z"
        fill="currentColor"
      />
    </svg>
    <div class="container pt-9 pt-lg-11 pb-7 position-relative z-index-1">
      <div class="row mb-7">
        <div class="col-md-4 mb-5 mb-md-0 text-md-center order-md-2">
          <router-link to="/" class="fs-3 link-hover-underline"
            >HappyHouse</router-link
          >
        </div>
        <div class="col-md-4 order-md-1 mb-3 mb-md-0">
          <a href="" class="fs-5 link-hover-underline"
            >SSAFY 7기 내꿈은 반포자이</a
          >
        </div>
        <div class="col-md-4 text-md-end order-md-3">
          <a href="" class="fs-5 link-hover-underline">+82 10-0000-0000</a>
        </div>
      </div>
      <hr class="my-5 my-lg-7" />
      <div class="row align-items-center">
        <div class="col-md-8 order-md-last mb-3 mb-md-0">
          <div class="d-flex flex-wrap justify-content-md-end me-n4">
            <a
              href="#!"
              class="small text-muted me-4 link-hover-underline my-2 block"
              >Properties</a
            >
            <a
              href="#!"
              class="small text-muted me-4 link-hover-underline my-2 block"
              >Careers</a
            >
            <a
              href="#!"
              class="small text-muted me-4 link-hover-underline my-2 block"
              >Company</a
            >
            <a
              href="#!"
              class="small text-muted me-4 link-hover-underline my-2 block"
              >Privacy policy</a
            >
          </div>
        </div>
        <div class="col-md-4 order-md-1 small">
          <span class="d-block my-2 lh-sm text-muted"
            >© Copyright . Kwon & Chu with SSAFY
          </span>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "FooterBar",
};
</script>

<style></style>
