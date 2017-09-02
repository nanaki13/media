
<?php
class Request
{
    public function __construct($server)
    {
	//echo 'construct' ;
        //print_r( $server) ;
    }

	public function process($config){
		//echo $_GET['page'].'.php';
		$page = new Page($config);
		$page->buildContent();
		//include ($_GET['page'].'.php');
	}
}
?>


