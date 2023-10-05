<template>
  <main>
    <!--hero-->
    <section class="position-relative jarallax">
      <!-- Background Image Parallax -->
      <img
        class="jarallax-img"
        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQONLt1f3Bg56Jbf1BF67bxkjPd4eaoKzKqlSczjlhe0RwJYyPqEhq7QJ6uzIYidguS_Pk&usqp=CAU"
        alt=""
      />
      <!--Overlay-->
      <div
        class="position-absolute start-0 top-0 w-100 h-100 bg-opacity-75"
      ></div>
      <div class="container py-12 position-relative z-index-1">
        <div class="row pt-7 pt-lg-12 justify-content-center">
          <div class="col-lg-10 position-relative z-index-2">
            <h1 class="display-1 text-center text-white mb-4">Happy House</h1>
            <h4 class="fw-bolder text-center mb-6 text-gradient-light">
              찾는 아파트가 있으신가요?
            </h4>
            <!--
            <form class="position-relative p-3 bg-white rounded-3 shadow-sm">
              <div class="row g-2 mx-0 align-items-end">
                <div class="col-md-12 col-lg-2">
                  <label for="p_for" class="form-label small text-muted block"
                    >검색 필터 :</label
                  >
                  <select
                    class="form-control pe-5"
                    data-choices='{"searchEnabled":false}'
                    name="p_for"
                    id="p_type"
                  >
                    <option value="dong">법정동</option>
                    <option value="aptName">아파트명</option>
                  </select>
                </div>
                <div class="col-md-9 col-lg-8">
                  <label for="p_location" class="form-label small text-muted"
                    >아파트 매물 검색 :
                  </label>
                  <div class="position-relative">
                    <i
                      class="bx bxs-map small ms-3 opacity-50 position-absolute start-0 top-50 translate-middle-y"
                    ></i>
                    <input
                      type="text"
                      id="p_location"
                      class="form-control ps-6"
                      placeholder="동 이름 또는 아파트명을 입력해주세요"
                    />
                  </div>
                </div>
                <div class="col-md-3 col-lg-2 text-md-end">
                  <button type="submit" class="btn btn-primary w-100">
                    <svg
                      xmlns="http://www.w3.org/2000/svg"
                      width="24"
                      height="24"
                      fill="none"
                      viewBox="0 0 24 24"
                      stroke="currentColor"
                    >
                      <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
                      />
                    </svg>
                  </button>
                </div>
              </div>
            </form>
            -->
            <small
              class="text-white text-opacity-100 pt-3 pt-lg-4 text-center d-block"
            >
              현재 서울에 5,989개의 매물이 있습니다
            </small>
          </div>
        </div>
      </div>
    </section>

    <section class="position-relative">
      <div class="container py-9 py-lg-11">
        <div class="d-flex mb-6 align-items-center">
          <h3 class="mb-0">추천 매물</h3>
          <div class="flex-grow-1 border-top mx-3"></div>
          <router-link
            class="flex-shrink-0 link-hover-underline"
            :to="{ name: 'house' }"
            >View All</router-link
          >
        </div>
        <div class="row justify-content-center">
          <div class="col-lg-4 col-md-6 col-sm-10 mb-5 mb-lg-0">
            <!--Card-property-2-->
            <div class="position-relative">
              <a href="#!" class="d-block card-hover overflow-hidden rounded-4">
                <!--Image-->
                <img
                  src="assets/img/used/아이파크.jpg"
                  class="img-fluid img-zoom"
                  alt=""
                />
                <!--Background-->
                <div
                  class="position-absolute start-0 top-0 w-100 h-100 bg-gradient-dark opacity-50"
                ></div>
                <div class="position-absolute start-0 top-0 w-100 pt-3 px-3">
                  <span class="badge bg-white text-primary">For Sale</span>
                </div>
                <div
                  class="text-white d-flex justify-content-between w-100 px-3 pb-4 position-absolute start-0 bottom-0 w-100 h-100 align-items-end"
                >
                  <div class="flex-grow-1 overflow-hidden pe-4">
                    <h5 class="mb-3">70억</h5>
                    <!--Location-->
                    <p class="mb-0 d-flex">
                      <i class="bx bxs-map me-1"></i>
                      <span class="small text-truncate"
                        >서울특별시 강남구 삼성동</span
                      >
                    </p>
                  </div>
                </div>
              </a>
              <div class="card-body pt-4">
                <a href="#!" class="text-dark d-block mb-4">
                  <h5>아이파크</h5>
                </a>
                <div class="row">
                  <div
                    class="col-3"
                    data-bs-toggle="tooltip"
                    data-bs-placement="top"
                    title=""
                    data-bs-original-title="Bedrooms"
                  >
                    <div class="d-flex align-items-center">
                      <i class="bx bx-bed fs-5 me-2"></i>
                      <strong class="small">12</strong>
                    </div>
                  </div>
                  <div
                    class="col-3 border-start border-end"
                    data-bs-toggle="tooltip"
                    data-bs-placement="top"
                    title=""
                    data-bs-original-title="Bathrooms"
                  >
                    <div class="d-flex align-items-center">
                      <i class="bx bx-bath fs-5 me-2"></i>
                      <strong class="small">5</strong>
                    </div>
                  </div>
                  <div
                    class="col-6"
                    data-bs-toggle="tooltip"
                    data-bs-placement="top"
                    title=""
                    data-bs-original-title="Area"
                  >
                    <div class="d-flex align-items-center">
                      <i class="bx bx-area fs-5 me-2"></i>
                      <strong class="small">2004년</strong>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-6 col-sm-10 mb-5 mb-lg-0">
            <!--Card-property-2-->
            <div class="position-relative">
              <a
                href="#!"
                class="d-block card-hover overflow-hidden rounded-4 position-relative"
              >
                <!--Image-->
                <img
                  src="assets/img/used/카일룸.jpg"
                  class="img-fluid rounded-4 img-zoom"
                  alt=""
                />
                <!--Background-->
                <div
                  class="position-absolute start-0 top-0 w-100 h-100 bg-gradient-dark opacity-75"
                ></div>
                <div class="position-absolute start-0 top-0 w-100 pt-3 px-3">
                  <span class="badge bg-white text-primary">For Sale</span>
                </div>
                <div
                  class="text-white d-flex justify-content-between w-100 px-3 pb-4 position-absolute start-0 bottom-0 w-100 h-100 align-items-end"
                >
                  <div class="flex-grow-1 overflow-hidden pe-4">
                    <h5 class="mb-3">64억 5천만원</h5>
                    <!--Location-->
                    <p class="mb-0 d-flex">
                      <i class="bx bxs-map me-1"></i>
                      <span class="small text-truncate"
                        >서울특별시 강남구 청담동</span
                      >
                    </p>
                  </div>
                </div>
              </a>
              <div class="card-body pt-4">
                <a href="#!" class="text-dark d-block mb-4">
                  <h5>상지리츠빌카일룸 2차</h5>
                </a>
                <div class="row">
                  <div
                    class="col-3"
                    data-bs-toggle="tooltip"
                    data-bs-placement="top"
                    title=""
                    data-bs-original-title="Bedrooms"
                  >
                    <div class="d-flex align-items-center">
                      <i class="bx bx-bed fs-5 me-2"></i>
                      <strong class="small">10</strong>
                    </div>
                  </div>
                  <div
                    class="col-3 border-start border-end"
                    data-bs-toggle="tooltip"
                    data-bs-placement="top"
                    title=""
                    data-bs-original-title="Bathrooms"
                  >
                    <div class="d-flex align-items-center">
                      <i class="bx bx-bath fs-5 me-2"></i>
                      <strong class="small">4</strong>
                    </div>
                  </div>
                  <div
                    class="col-6"
                    data-bs-toggle="tooltip"
                    data-bs-placement="top"
                    title=""
                    data-bs-original-title="Area"
                  >
                    <div class="d-flex align-items-center">
                      <i class="bx bx-area fs-5 me-2"></i>
                      <strong class="small">2006년</strong>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-6 col-sm-10 mb-5 mb-lg-0">
            <!--Card-property-2-->
            <div class="position-relative">
              <a
                href="#!"
                class="d-block card-hover overflow-hidden rounded-4 position-relative"
              >
                <!--Image-->
                <img
                  src="assets/img/used/아크로.jpg"
                  class="img-fluid img-zoom"
                  alt=""
                />
                <!--Background-->
                <div
                  class="position-absolute rounded-4 start-0 top-0 w-100 h-100 bg-gradient-dark opacity-50"
                ></div>
                <div class="position-absolute start-0 top-0 w-100 pt-3 px-3">
                  <span class="badge bg-white text-primary">For Sale</span>
                </div>
                <div
                  class="text-white d-flex justify-content-between w-100 px-3 pb-4 position-absolute start-0 bottom-0 w-100 h-100 align-items-end"
                >
                  <div class="flex-grow-1 overflow-hidden pe-4">
                    <h5 class="mb-3">43억 8천만원</h5>
                    <!--Location-->
                    <p class="mb-0 d-flex">
                      <i class="bx bxs-map me-1"></i>
                      <span class="small text-truncate"
                        >서울특별시 서초구 반포동</span
                      >
                    </p>
                  </div>
                </div>
              </a>
              <div class="card-body pt-4">
                <a href="#!" class="text-dark d-block mb-4">
                  <h5>아크로 리버파크</h5>
                </a>
                <div class="row">
                  <div
                    class="col-3"
                    data-bs-toggle="tooltip"
                    data-bs-placement="top"
                    title=""
                    data-bs-original-title="Bedrooms"
                  >
                    <div class="d-flex align-items-center">
                      <i class="bx bx-bed fs-5 me-2"></i>
                      <strong class="small">6</strong>
                    </div>
                  </div>
                  <div
                    class="col-3 border-start border-end"
                    data-bs-toggle="tooltip"
                    data-bs-placement="top"
                    title=""
                    data-bs-original-title="Bathrooms"
                  >
                    <div class="d-flex align-items-center">
                      <i class="bx bx-bath fs-5 me-2"></i>
                      <strong class="small">3</strong>
                    </div>
                  </div>
                  <div
                    class="col-6"
                    data-bs-toggle="tooltip"
                    data-bs-placement="top"
                    title=""
                    data-bs-original-title="Area"
                  >
                    <div class="d-flex align-items-center">
                      <i class="bx bx-area fs-5 me-2"></i>
                      <strong class="small">2016년</strong>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="position-relative overflow-hidden">
      <!--Circle line-->
      <svg
        class="position-absolute start-0 flip-x bottom-0 mb-n4 h-100 w-auto text-warning"
        width="324"
        height="324"
        viewBox="0 0 324 324"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          d="M324 0.999969C281.583 0.999971 239.581 9.35461 200.393 25.5869C161.205 41.8192 125.598 65.6112 95.6045 95.6045C65.6112 125.598 41.8192 161.205 25.5869 200.393C9.35463 239.581 0.999996 281.583 1 324"
          stroke-width=".5"
          stroke="currentColor"
        />
      </svg>
      <div class="container py-9 py-lg-11 position-relative">
        <div class="row align-items-center">
          <div
            class="col-lg-6 mb-5 mb-md-0"
            data-aos="fade-up"
            data-aos-delay="100"
          >
            <div class="position-relative ps-12 pb-12">
              <img
                src="assets/img/used/흑석.jpg"
                class="img-fluid rounded-4"
                alt=""
              />
              <img
                src="assets/img/used/반포자이.jpg"
                class="img-fluid w-50 rounded-4 position-absolute bottom-0 start-0 ml-3"
                alt=""
              />
            </div>
          </div>
          <div class="col-lg-6 col-xl-5 ms-lg-auto">
            <h5 class="fw-bold mb-4" data-aos="fade-up" data-aos-delay="100">
              Our Vision
            </h5>
            <h3 class="fs-2 fw-normal" data-aos="fade-up" data-aos-delay="150">
              항상 고객님이 최적의 조건으로 <br />
              매매할 수 있도록 고심합니다.
            </h3>
          </div>
        </div>
      </div>
    </section>

    <section class="position-relative overflow-hidden">
      <!--Circle line-->
      <svg
        class="position-absolute end-0 bottom-0 h-80 w-auto text-gray-200"
        width="324"
        height="324"
        viewBox="0 0 324 324"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          d="M324 0.999969C281.583 0.999971 239.581 9.35461 200.393 25.5869C161.205 41.8192 125.598 65.6112 95.6045 95.6045C65.6112 125.598 41.8192 161.205 25.5869 200.393C9.35463 239.581 0.999996 281.583 1 324"
          stroke-width=".5"
          stroke="currentColor"
        />
      </svg>

      <div class="container py-9 py-lg-11 position-relative z-index-1">
        <!--Masonry layout-->
        <div class="row" data-isotope='{"layoutMode":"masonry"}'>
          <!--Masonry-Grid-item-->
          <div class="col-md-4 col-6 mb-4 grid-item">
            <h3 data-aos="fade-up">오늘의 인기 매물</h3>
            <p data-aos="fade-up" data-aos-delay="100">
              하루동안 가장 많은 고객님이 찾아주신 매물입니다.
            </p>
          </div>
          <!--Masonry-Grid-item-->
          <div data-aos="fade-up" class="col-md-4 col-6 mb-4 grid-item">
            <a
              href="#!"
              class="position-relative card-hover overflow-hidden card border hover-lift hover-shadow-xl rounded-3 border-0"
            >
              <div class="position-relative overflow-hidden">
                <img
                  src="assets/img/used/시그니엘.jpg"
                  class="img-zoom img-fluid"
                  alt=""
                />
                <!--Overlay background-->
                <div
                  class="bg-gradient-dark opacity-50 position-absolute start-0 top-0 w-100 h-100"
                ></div>
                <!--Overlay content-->
                <div
                  class="position-absolute start-0 text-white d-flex flex-column align-items-center justify-content-end top-0 w-100 h-100 p-2 p-md-4"
                >
                  <h5>시그니엘 서울</h5>
                  <p class="mb-0 opacity-75">서울특별시 송파구</p>
                </div>
              </div>
            </a>
          </div>
          <!--Masonry-Grid-item-->
          <div data-aos="fade-up" class="col-md-4 col-6 mb-4 grid-item">
            <a
              href="#!"
              class="position-relative card-hover overflow-hidden card border hover-lift hover-shadow-xl rounded-3 border-0"
            >
              <div class="position-relative overflow-hidden">
                <img
                  src="assets/img/used/롯데캐슬.jpg"
                  class="img-zoom img-fluid"
                  alt=""
                />
                <!--Overlay background-->
                <div
                  class="bg-gradient-dark opacity-50 position-absolute start-0 top-0 w-100 h-100"
                ></div>
                <!--Overlay content-->
                <div
                  class="position-absolute start-0 text-white d-flex flex-column align-items-center justify-content-end top-0 w-100 h-100 p-2 p-md-4"
                >
                  <h5>롯데캐슬</h5>
                  <p class="mb-0 opacity-75">서울특별시 서초구</p>
                </div>
              </div>
            </a>
          </div>
          <!--Masonry-Grid-item-->
          <div data-aos="fade-up" class="col-md-4 col-6 mb-4 grid-item">
            <a
              href="#!"
              class="position-relative card-hover overflow-hidden card border hover-lift hover-shadow-xl rounded-3 border-0"
            >
              <div class="position-relative overflow-hidden">
                <img
                  src="assets/img/used/트리지움.jpg"
                  class="img-zoom img-fluid"
                  alt=""
                />
                <!--Overlay background-->
                <div
                  class="bg-gradient-dark opacity-50 position-absolute start-0 top-0 w-100 h-100"
                ></div>
                <!--Overlay content-->
                <div
                  class="position-absolute start-0 text-white d-flex flex-column align-items-center justify-content-end top-0 w-100 h-100 p-2 p-md-4"
                >
                  <h5>트리지움</h5>
                  <p class="mb-0 opacity-75">서울특별시 송파구</p>
                </div>
              </div>
            </a>
          </div>

          <!--Masonry-Grid-item-->
          <div data-aos="fade-up" class="col-md-4 col-6 mb-4 grid-item">
            <a
              href="#!"
              class="position-relative card-hover overflow-hidden card border hover-lift hover-shadow-xl rounded-3 border-0"
            >
              <div class="position-relative overflow-hidden">
                <img
                  src="assets/img/used/한양.jpg"
                  class="img-zoom img-fluid"
                  alt=""
                />
                <!--Overlay background-->
                <div
                  class="bg-gradient-dark opacity-50 position-absolute start-0 top-0 w-100 h-100"
                ></div>
                <!--Overlay content-->
                <div
                  class="position-absolute start-0 text-white d-flex flex-column align-items-center justify-content-end top-0 w-100 h-100 p-2 p-md-4"
                >
                  <h5>한양아파트</h5>
                  <p class="mb-0 opacity-75">서울특별시 강남구</p>
                </div>
              </div>
            </a>
          </div>
          <!--Masonry-Grid-item-->
          <div data-aos="fade-up" class="col-md-4 col-6 mb-4 grid-item">
            <a
              href="#!"
              class="position-relative card-hover overflow-hidden card border hover-lift hover-shadow-xl rounded-3 border-0"
            >
              <div class="position-relative overflow-hidden">
                <img
                  src="assets/img/used/힐스테이트.jpg"
                  class="img-zoom img-fluid"
                  alt=""
                />
                <!--Overlay background-->
                <div
                  class="bg-gradient-dark opacity-50 position-absolute start-0 top-0 w-100 h-100"
                ></div>
                <!--Overlay content-->
                <div
                  class="position-absolute start-0 text-white d-flex flex-column align-items-center justify-content-end top-0 w-100 h-100 p-2 p-md-4"
                >
                  <h5>힐스테이트</h5>
                  <p class="mb-0 opacity-75">서울특별시 성동구</p>
                </div>
              </div>
            </a>
          </div>
        </div>
      </div>
    </section>

    <section>
      <div class="container pb-9 pb-lg-11 mt-8">
        <div
          class="px-4 rounded-3 shadow-lg py-6 px-lg-5 py-lg-7 bg-gradient-primary text-white position-relative overflow-hidden"
          data-aos="fade-up"
          data-aos-duration="400"
        >
          <svg
            class="position-absolute end-0 bottom-0 mb-4 text-white opacity-25"
            width="200"
            height="400"
            preserveAspectRatio="none"
            viewBox="0 0 150 300"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M150 300C130.302 300 110.796 296.12 92.5975 288.582C74.3986 281.044 57.8628 269.995 43.934 256.066C30.0052 242.137 18.9563 225.601 11.4181 207.403C3.87986 189.204 -6.2614e-07 169.698 0 150C6.2614e-07 130.302 3.87987 110.796 11.4181 92.5975C18.9563 74.3987 30.0052 57.8628 43.934 43.934C57.8628 30.0052 74.3987 18.9563 92.5975 11.4181C110.796 3.87986 130.302 3.51961e-06 150 5.00679e-06L150 37.5C135.226 37.5 120.597 40.4099 106.948 46.0636C93.299 51.7172 80.8971 60.0039 70.4505 70.4505C60.0039 80.8971 51.7172 93.299 46.0636 106.948C40.4099 120.597 37.5 135.226 37.5 150C37.5 164.774 40.4099 179.403 46.0636 193.052C51.7172 206.701 60.0039 219.103 70.4505 229.55C80.8971 239.996 93.299 248.283 106.948 253.936C120.597 259.59 135.226 262.5 150 262.5V300Z"
              fill="currentColor"
            />
          </svg>

          <div class="row align-items-end position-relative">
            <div class="col-lg-6 col-xl-7">
              <h5 class="text-muted mb-4">
                오늘의 아파트 매물이 궁금하신가요?
              </h5>
              <h1 class="mb-5 mb-md-0 display-4">추천 매물 보러가기</h1>
            </div>
            <div class="col-lg-6 col-xl-5 text-lg-end">
              <router-link
                class="btn btn-lg btn-rise btn-outline-white rounded-pill me-2 mb-2 mb-lg-0"
                style="font-size: 50px"
                :to="{ name: 'house' }"
              >
                <span class="btn-rise-bg bg-white"></span>
                <span class="btn-rise-text">GO!</span>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
export default {
  name: "HomeView",
};
</script>
