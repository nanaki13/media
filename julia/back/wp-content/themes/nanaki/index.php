<?php
// charge le fichier header.php du thème
get_header();
?>
<div id="wrapper" class="container">
	<?php if ( has_nav_menu('topmenu') ) : ?>
	<nav role="navigation" id="topmenu">
		<?php wp_nav_menu( array( 'theme_location' => 'topmenu' ) ); ?>
	</nav>
	<?php endif; ?>

	<div id="header">
		<div class="inner clearfix">
			<?php if ( get_header_image() ) : ?>
			<a id="logo" href="<?php echo esc_url( home_url( '/' ) ); ?>" rel="home">
				<img src="<?php header_image(); ?>" width="<?php echo get_custom_header()->width; ?>" height="<?php echo get_custom_header()->height; ?>" alt="<?php bloginfo( 'name' ) ?>"> 
			</a>
			<?php endif; ?>
			<?php if (is_active_sidebar( 'header' )) : ?>
			<div id="headerwidget">
				<div class="inner clearfix">
					<?php dynamic_sidebar( 'header' ); ?>
				</div>
			</div>
			<?php endif; ?>
		</div>
	</div>

	<?php
	$nbwidgetstop = (bool)is_active_sidebar( 'top1' ) + (bool)is_active_sidebar( 'top2' ) + (bool)is_active_sidebar( 'top3' ) + (bool)is_active_sidebar( 'top4' );
	?>
	<?php if ($nbwidgetstop) : ?>
	<div id="top">
		<div class="inner clearfix <?php echo 'n'.$nbwidgetstop ?>">
			<?php if (is_active_sidebar( 'top1' )) : ?>
			<div id="sidebartop1">
				<div class="inner clearfix">
					<?php dynamic_sidebar( 'top1' ); ?>
				</div>
			</div>
			<?php endif; ?>
			<?php if (is_active_sidebar( 'top2' )) : ?>
			<div id="sidebartop2">
				<div class="inner clearfix">
					<?php dynamic_sidebar( 'top2' ); ?>
				</div>
			</div>
			<?php endif; ?>
			<?php if (is_active_sidebar( 'top3' )) : ?>
			<div id="sidebartop3">
				<div class="inner clearfix">
					<?php dynamic_sidebar( 'top3' ); ?>
				</div>
			</div>
			<?php endif; ?>
			<?php if (is_active_sidebar( 'top4' )) : ?>
			<div id="sidebartop4">
				<div class="inner clearfix">
					<?php dynamic_sidebar( 'top4' ); ?>
				</div>
			</div>
			<?php endif; ?>
			<div class="clr"></div>
		</div>
	</div>
	<?php endif; ?>

	<?php if (is_active_sidebar( 'slideshow' )) : ?>
	<div id="slideshow">
		<div class="inner clearfix">
			<?php dynamic_sidebar( 'slideshow' ); ?>
		</div>
	</div>
	<?php endif; ?>

	<?php
	$mainclass = '';
	if (! is_active_sidebar( 'left' ) )
	{
		$mainclass .= " noleft";
	}
	if (! is_active_sidebar( 'right' ) )
	{
		$mainclass .= " noright";
	}
	$mainclass = trim($mainclass);
	?>
	<div id="main" class="clearfix <?php echo $mainclass ?>">
		<?php if (is_active_sidebar( 'left' )) : ?>
		<div id="left">
			<div class="inner">
				<?php dynamic_sidebar( 'left' ); ?>
			</div>
		</div>
		<?php endif; ?>
		<div id="center">
			  <div class="inner">
				<?php if ( have_posts() ) : ?>
					<?php /* Start the Loop */ ?>
					<?php while ( have_posts() ) : the_post(); ?>
						<article id="post-<?php the_ID(); ?>" <?php post_class(); ?>>
							<header>
								<?php if ( is_single() ) :
									the_title( '<h2 class="entry-title">', '</h2>' );
								else :
									the_title( '<h2 class="entry-title"><a href="' . esc_url( get_permalink() ) . '" rel="bookmark">', '</a></h2>' );
								endif;
								?>
							</header>
							<?php the_content(); ?>
						</article>
					<?php endwhile; ?>
				<?php endif; ?>
			</div>
		</div>
		<?php if (is_active_sidebar( 'right' )) : ?>
		<div id="right">
			  <div class="inner">
				<?php dynamic_sidebar( 'right' ); ?>
			  </div>
		</div>
		<?php endif; ?>
	</div>
</div>

<div id="footerwrap">
	<div id="footer" class="container">
		<div class="inner">
			<?php dynamic_sidebar( 'footer' ); ?>
		</div>
	</div>
</div>

<?php get_footer(); ?>
