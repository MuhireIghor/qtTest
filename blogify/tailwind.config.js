import colors from "tailwindcss/colors";
/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html", "./src/**/*.{js,jsx,tsx,,ts,html}"
  ],
  theme: {
    extend: {
      colors: {
        primary: "#605BFF",
        input:"#e1f0ef"

      }
    },
    container: {
      center: true,
      padding: "2rem",
      screens: {
        "2xl": "1400px",
      },
    },
    colors: {
      primary: "var(--color-primary)",
      accent: "var(--color-accent)",
      ...colors,
    },
    fontFamily: {
      mulish: "Mulish, sans-serif",
      montserrat: "Montserrat, sans-serif",
    },
    screens: {
      xxs: "280px",
      "2xs": "320px",
      xs: "480px",
      "2sm": "542px",
      sm: "640px",
      md: "768px",
      "2md": "900px",
      lg: "1024px",
      "2lg": "1150px",
      xl: "1280px",
      "2xl": "1400px",
      xxl: "1600px",
    },

  },
  plugins: [],
}

