<?php
// définit les menus
register_nav_menus( array(
	'topmenu'   => __( 'Top menu', '1870wp' )
) );

// active l'utilisation d'une en-tête
$args = array(
	'width'         => 376,
	'height'        => 61,
	'default-image' => get_template_directory_uri() . '/images/logo.png',
);
add_theme_support( 'custom-header', $args );

// définit les zones de widgets
function theme1870wp_widgets_init() {

	register_sidebar( array(
		'name'          => __( 'Header', '1870wp' ),
		'id'            => 'header',
		'description'   => __( 'Zone de widget dans l entete.', '1870wp' ),
		'before_widget' => '<aside id="%1$s" class="widget %2$s">',
		'after_widget'  => '</aside>',
		'before_title'  => '<h3 class="widget-title">',
		'after_title'   => '</h3>',
	) );

	register_sidebar( array(
		'name'          => __( 'Top1', '1870wp' ),
		'id'            => 'top1',
		'description'   => __( 'Zone de widget top1.', '1870wp' ),
		'before_widget' => '<aside id="%1$s" class="widget %2$s">',
		'after_widget'  => '</aside>',
		'before_title'  => '<h3 class="widget-title">',
		'after_title'   => '</h3>',
	) );

	register_sidebar( array(
		'name'          => __( 'Top2', '1870wp' ),
		'id'            => 'top2',
		'description'   => __( 'Zone de widget top2.', '1870wp' ),
		'before_widget' => '<aside id="%1$s" class="widget %2$s">',
		'after_widget'  => '</aside>',
		'before_title'  => '<h3 class="widget-title">',
		'after_title'   => '</h3>',
	) );

	register_sidebar( array(
		'name'          => __( 'Top3', '1870wp' ),
		'id'            => 'top3',
		'description'   => __( 'Zone de widget top3.', '1870wp' ),
		'before_widget' => '<aside id="%1$s" class="widget %2$s">',
		'after_widget'  => '</aside>',
		'before_title'  => '<h3 class="widget-title">',
		'after_title'   => '</h3>',
	) );

	register_sidebar( array(
		'name'          => __( 'Top4', '1870wp' ),
		'id'            => 'top4',
		'description'   => __( 'Zone de widget top4.', '1870wp' ),
		'before_widget' => '<aside id="%1$s" class="widget %2$s">',
		'after_widget'  => '</aside>',
		'before_title'  => '<h3 class="widget-title">',
		'after_title'   => '</h3>',
	) );

	register_sidebar( array(
		'name'          => __( 'Slideshow', '1870wp' ),
		'id'            => 'slideshow',
		'description'   => __( 'Zone de widget pour le plugin Slideshow CK.', '1870wp' ),
		'before_widget' => '<aside id="%1$s" class="widget %2$s">',
		'after_widget'  => '</aside>',
		'before_title'  => '<h3 class="widget-title">',
		'after_title'   => '</h3>',
	) );

	register_sidebar( array(
		'name'          => __( 'Left', '1870wp' ),
		'id'            => 'left',
		'description'   => __( 'Zone de widget left.', '1870wp' ),
		'before_widget' => '<aside id="%1$s" class="widget %2$s">',
		'after_widget'  => '</aside>',
		'before_title'  => '<h3 class="widget-title">',
		'after_title'   => '</h3>',
	) );

	register_sidebar( array(
		'name'          => __( 'Right', '1870wp' ),
		'id'            => 'right',
		'description'   => __( 'Zone de widget right.', '1870wp' ),
		'before_widget' => '<aside id="%1$s" class="widget %2$s">',
		'after_widget'  => '</aside>',
		'before_title'  => '<h3 class="widget-title">',
		'after_title'   => '</h3>',
	) );

	register_sidebar( array(
		'name'          => __( 'Footer', '1870wp' ),
		'id'            => 'footer',
		'description'   => __( 'Zone de widget footer.', '1870wp' ),
		'before_widget' => '<aside id="%1$s" class="widget %2$s">',
		'after_widget'  => '</aside>',
		'before_title'  => '<h3 class="widget-title">',
		'after_title'   => '</h3>',
	) );
}
add_action( 'widgets_init', 'theme1870wp_widgets_init' );

// crée les options pour le thème
function theme1870wp_customize_register($wp_customize){
	$wp_customize->add_section(
		'options_perso',
		array(
			'title' => __( 'Mes options persos', '1870wp' ),
			'priority'   => 100,
			'capability' => 'edit_theme_options',
			'description' => __( 'Personnaliser le theme avec mes options.', 'theme1870wp' )
		)
	);

	$wp_customize->add_setting('theme1870wp_options[bg_color]', array(
		'capability' => 'edit_theme_options',
		'type'       => 'option',
		'default'       => '',
	));
	 
	$wp_customize->add_control(new WP_Customize_Color_Control( 
	$wp_customize, 'theme1870wp_options[bg_color]', array(
		'settings' => 'theme1870wp_options[bg_color]',
		'label'    => __('Couleur de fond', '1870wp'),
		'section'  => 'options_perso'
		)
	));
}
add_action('customize_register', 'theme1870wp_customize_register');
// à utiliser avec echo theme1870wp_options('bg_color');
function theme1870wp_options($name, $default = false) {
    $options = ( get_option( 'theme1870wp_options' ) ) ? get_option( 'theme1870wp_options' ) : null;
    // return the option if it exists
    if ( isset( $options[ $name ] ) ) {
        return apply_filters( 'theme1870wp_options_$name', $options[ $name ] );
    }
    // return default if nothing else
    return apply_filters( 'theme1870wp_options_$name', $default );
}

