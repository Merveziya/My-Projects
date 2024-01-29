cmake_minimum_required(VERSION 3.26)
project(untitled3)

find_package(PkgConfig REQUIRED)

pkg_check_modules(SDL2 REQUIRED IMPORTED_TARGET sdl2)
pkg_check_modules(SDL2_TTF REQUIRED IMPORTED_TARGET SDL2_ttf)
pkg_check_modules(CURL REQUIRED IMPORTED_TARGET libcurl)

include_directories(
        ${SDL2_INCLUDE_DIRS}
        ${SDL2_TTF_INCLUDE_DIRS}
        ${CURL_INCLUDE_DIRS}
)

add_executable(untitled3 main.c
)

target_link_libraries(${PROJECT_NAME} PUBLIC
        PkgConfig::SDL2
        PkgConfig::SDL2_TTF
        PkgConfig::CURL
)
