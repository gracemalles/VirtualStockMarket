<template>
  <div id="app">
    <Particles id="tsparticles" :options="{
 
 
  fullScreen: true,
  interactivity: {
    detectsOn: 'window'
  },
  emitters: {
      life: {
          duration: 5,
          count: 1
      },
    position: {
      x: 50,
      y: 0,
      direction: top

    },
    rate: {
      quantity: 10,
      delay: 0.25
    }
  },
  particles: {
    color: {
      value: ['#1E00FF', '#FF0061', '#E1FF00', '#00FF9E']
    },
    move: {
      decay: 0.05,
      direction: 'top',
      enable: true,
      gravity: {
        enable: true
      },
      outModes: {
        top: 'none',
        default: 'destroy'
      },
      speed: { min: 25, max: 50 }
    },
    number: {
      value: 0
    },
    opacity: {
      value: 1
    },
    rotate: {
      value: {
        min: 0,
        max: 360
      },
      direction: 'random',
      animation: {
        enable: true,
        speed: 30
      }
    },
    tilt: {
      direction: 'random',
      enable: true,
      value: {
        min: 0,
        max: 360
      },
      animation: {
        enable: true,
        speed: 30
      }
    },
    size: {
      value: 8
    },
    roll: {
      darken: {
        enable: true,
        value: 25
      },
      enable: true,
      speed: {
        min: 5,
        max: 15
      }
    },
    wobble: {
      distance: 30,
      enable: true,
      speed: {
        min: -7,
        max: 7
      }
    },
    shape: {
      type: [
        'circle',
        'square',
        'polygon',
        'character',
        'character',
        'character',
        'image',
        'image',
        'image'
      ],
      options: {
        image: [
          {
            src: 'https://particles.js.org/images/fruits/apple.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/avocado.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/banana.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/berries.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/cherry.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/grapes.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/lemon.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/orange.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/peach.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/pear.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/pepper.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/plum.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/star.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/strawberry.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/watermelon.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          },
          {
            src: 'https://particles.js.org/images/fruits/watermelon_slice.png',
            width: 32,
            height: 32,
            particles: {
              size: {
                value: 16
              }
            }
          }
        ],
        polygon: [
          {
            sides: 5
          },
          {
            sides: 6
          }
        ],
        character: [
          {
            fill: true,
            font: 'Verdana',
            value: [ '🤡', '🍀', '🍙', '🦄', '⭐️'],
            style: '',
            weight: 400
          }
        ]
      }
    }
  }
}" />
  </div>
</template>

<script>

</script>

<style>
#App {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

#tsparticles {
  position: fixed;
  width: 100%;
  height: 1px;
  top: 6vh;
  left: 0;
  z-index: -1;
}
</style>
